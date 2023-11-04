package com.example.preggycare.listners;

import com.example.preggycare.modelclass.usermed;
import com.example.preggycare.modelclass.usernote;
import com.example.preggycare.modelclass.usertest;

public interface NoteListner {
        void onNoteClicked(usernote usernote, int position);

    }
