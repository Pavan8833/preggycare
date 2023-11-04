package com.example.preggycare;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;

import com.example.preggycare.listners.PdfItemClickListener;
import com.example.preggycare.modelclass.Mydatabase;
import com.example.preggycare.adapters.pdfadapter;
import com.example.preggycare.modelclass.userpdf;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class uploadphoto extends AppCompatActivity implements PdfItemClickListener {

    FloatingActionButton pdfbtn;
    RecyclerView  pdfrecycler;
    SharedPreferences shp;
    pdfadapter pdfadapter;

    private ActivityResultLauncher<String> openFileManagerLauncher;


    static final int PICK_PDF_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadphoto);

        shp = getSharedPreferences("mypreferences",MODE_PRIVATE);
        String num = shp.getString("usernumber","");

        pdfbtn = findViewById(R.id.pdfsbtn);
        pdfrecycler = findViewById(R.id.pdfsrecycler);
        pdfadapter = new pdfadapter(this,this);


        pdfrecycler.setLayoutManager(new LinearLayoutManager(this));

        Mydatabase.getInstance(getApplicationContext()).userpdfdao().getallpdf(num).observe(this,pdflist -> {
            pdfadapter.setpdfFiles(pdflist);
            pdfrecycler.setAdapter(pdfadapter);
        });

        openFileManagerLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {

                        String fileName = getFileNameFromUri(uri);
                        userpdf userpdf =  new userpdf();
                        userpdf.setFilename(fileName);
                        userpdf.setFilepath(uri.toString());
                        userpdf.setUsernum(num);
                       Mydatabase.getInstance(this).userpdfdao().insert(userpdf);
                    }
                });

        pdfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFileManagerLauncher.launch("application/pdf");

            }
        });



    }

    @SuppressLint("Range")
    private String getFileNameFromUri(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }

    @Override
    public void onPdfItemClick(userpdf userpdf) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri pdfUri = Uri.parse(userpdf.getFilepath());
        intent.setDataAndType(pdfUri, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
