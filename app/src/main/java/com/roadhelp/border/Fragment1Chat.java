package com.roadhelp.border;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class Fragment1Chat extends Fragment {

    private ListView messageListView;
    private MessageAdapter messageAdapter;
    private ProgressBar progressBar;
    private ImageButton sendImageButton;
    private Button sendMessageButton;
    private EditText messageEditText;
    private static final int RC_IAMGE_PICKER = 123;


    private String userName;
    List<MessageChat> messageChats;


    FirebaseDatabase database;
    DatabaseReference messagesDatabaseReference;
    ChildEventListener messagesChildEventListener;

    private FirebaseStorage storage;
    private StorageReference chatImagesStorageReference;


    public static Fragment1Chat newInstance(String title) {  // метод принимает 1 строку и запихивает их в bundle args и возвращает фрагмент
        Bundle args = new Bundle();
        args.putString("TITLE", title);


        Fragment1Chat fragment1Chat = new Fragment1Chat();
        fragment1Chat.setArguments(args);
        return fragment1Chat;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String title = getArguments().getString("TITLE");

        FirebaseApp.initializeApp(getContext());
        database = FirebaseDatabase.getInstance();
        messagesDatabaseReference = database.getReference().child(title);

        userName = "Default user";//


        progressBar = view.findViewById(R.id.progressBar);
        sendImageButton = view.findViewById(R.id.sendPhotoButton);
        sendMessageButton = view.findViewById(R.id.sendMessageButtom);
        messageEditText = view.findViewById(R.id.editMessageText);

        storage = FirebaseStorage.getInstance();
        chatImagesStorageReference = storage.getReference().child("chat_images");

        messageListView = view.findViewById(R.id.messageListView);
        messageChats = new ArrayList<>(); //создаёт массив объектов класса MessageChat
        messageAdapter = new MessageAdapter(getContext(), R.layout.message_item, messageChats);
        messageListView.setAdapter(messageAdapter);




        progressBar.setVisibility(ProgressBar.INVISIBLE);

        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() > 0) {
                    sendMessageButton.setEnabled(true);
                }
                else {sendMessageButton.setEnabled(false);}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        messageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});



        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MessageChat messageChat1 = new MessageChat();
                messageChat1.setText(messageEditText.getText().toString());
                messageChat1.setName(userName);
                messageChat1.setImageUrl(null);

                messagesDatabaseReference.push().setValue(messageChat1);

                messageEditText.setText("");

            }
        });





        messagesChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MessageChat messageChat = snapshot.getValue(MessageChat.class);
                messageAdapter.add(messageChat);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        messagesDatabaseReference.addChildEventListener(messagesChildEventListener);


        sendImageButton = view.findViewById(R.id.sendPhotoButton);

        sendImageButton.setOnClickListener(new View.OnClickListener() { // в случае клика по изображению вместо send
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.setType("image/*");
                intent1.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent1, "Choose an image"), RC_IAMGE_PICKER);

            }
        });



    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { // когда выбрана картинка
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_IAMGE_PICKER && resultCode == RESULT_OK){
            Uri selectedImageUri = data.getData(); // получаем uri картинки из data
            final StorageReference imageReference = chatImagesStorageReference.child(selectedImageUri.getLastPathSegment());
            UploadTask uploadTask;
            uploadTask = imageReference.putFile(selectedImageUri); // загружаем картинку на Firebase Storage

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return imageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult(); // скачиваем картинку с Firebase Storage
                        MessageChat message = new MessageChat();    //создаём объект класса со ссылкой на картинку
                        message.setImageUrl(downloadUri.toString());
                        message.setName(userName);
                        messagesDatabaseReference.push().setValue(message);
                    } else {
                        // Handle failures
                        // ...
                    }
                }
            });

        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        messagesDatabaseReference.removeEventListener(messagesChildEventListener); //причина всех бед)) надо отвязыать листенер, потому что создаётся постоянно новый. изучай lifecycle fragment
    }





}
