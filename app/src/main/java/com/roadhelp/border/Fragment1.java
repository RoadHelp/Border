package com.roadhelp.border;

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

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {

    private ListView messageListView;
    private MessageAdapter messageAdapter;
    private ProgressBar progressBar;
    private ImageButton sendImageButton;
    private Button sendMessageButton;
    private EditText messageEditText;

    private String userName;


    FirebaseDatabase database;
    DatabaseReference messagesDatabaseReference;
    ChildEventListener messagesChildEventListener;


    public static Fragment1 newInstance(String title) {  // метод принимает 2 строки и запихивает их в bundle args и возвращает фрагмент
        Bundle args = new Bundle();
        args.putString("TITLE", title);


        Fragment1 fragment1 = new Fragment1();
        fragment1.setArguments(args);
        return fragment1;
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

        messageListView = view.findViewById(R.id.messageListView);
        List<MessageChat> messageChats = new ArrayList<>(); //создаёт массив объектов класса MessageChat





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

                MessageChat messageChat = new MessageChat();
                messageChat.setText(messageEditText.getText().toString());
                messageChat.setName(userName);
                messageChat.setImageUrl(null);

                messagesDatabaseReference.push().setValue(messageChat);

                messageEditText.setText("");

            }
        });

        sendImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        messagesChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //адаптер связывает массив объектов класса и слой разметки message_item
                //именно создание адаптера и установка вне onChildAdded создавали дубликаты сообщений, на решение проблемы убил много времени
                messageAdapter = new MessageAdapter(getContext(), R.layout.message_item, messageChats);
                messageListView.setAdapter(messageAdapter);


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
    }
}
