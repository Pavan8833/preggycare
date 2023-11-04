package com.example.preggycare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.preggycare.adapters.Adapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.content.Intent;

import java.util.ArrayList;

public class faq extends AppCompatActivity {

    ArrayList<Recyclemodel> list;
    RecyclerView recyclerView;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        recyclerView = findViewById(R.id.recycleview);
        list = new ArrayList<>();

        list.add(new Recyclemodel("What are the early signs of pregnancy?","Common early signs of pregnancy are missed periods, nausea, morning sickness, breast changes, fatigue, Increased urination, changes in appetite, frequent headaches",false));
        list.add(new Recyclemodel("How can I prevent stretch marks as a pregenant women?","Keep skin moisturized, maintain healthy weight gain, stay hydrated , Eat a nutrient-rich diet , Include vitamin C in ur diet, Exercise regularly , Avoid rapid weight gain or loss",false));
        list.add(new Recyclemodel("What foods should I avoid during pregnancy?","Foods to avoid or limit during pregnancy are Excessive caffeine, Alcohol, Raw/undercooked meat & eggs, Unwashed fruits and vegetables, High mercury fish, Excessive Oil & Sicy foods, Artificial sweeteners",false));
        list.add(new Recyclemodel("What are the stages of pregnancy?","1st trimester(Week 1 - Week 12), \n" +
                "2nd trimester(Week 13 - Week 27),\n" +
                "3rd trimester(Week 28 - Week 40)",false));
        list.add(new Recyclemodel("Is it safe to exercise during pregnancy?","Yes! \n" +
                "\"Exercise is essential for optimal health – both for mind and body\"\n" +
                "But be Sure on below mentioned exercises ;\n" +
                "Safe : Yoga , Walking , Swimming , Cycling , Jogging \n" +
                "Unsafe : Bending backwards , Excercises that causes dehydration , Gymnastics , Horse riding , Lifting Heavy weights ",false));
        list.add(new Recyclemodel("What prenatal vitamins should I take?","Folic Acid(Folate) : 400-800 micrograms (mcg)\n" +
                "Iron : 27-30 milligrams (mg)\n" +
                "Calcium : 1000-1300 mg\n" +
                "Vitamin D :  600-1000 international units (IU)\n" +
                "Vitamin C : 70-85 mg\n" +
                "Vitamin E : 15-20 mg\n" +
                "Vitamin B6 : 1.9-2.6 mg\n" +
                "Vitamin B12 : 2.6-2.8 mcg\n" +
                "Omega-3 Fatty Acids(DHA and EPA) : 200-300 mg of DHA & 220-320 mg of EPA\n" +
                "Iodine : 150-220 mcg",false));
        list.add(new Recyclemodel("What are the different types of delivery methods?","Vaginal Delivery : Baby is born through birth canal naturally\n" +
                "Cesarean Section (C-Section) : Baby is delivered through a surgical incision in the abdomen and uterus.\n" +
                "VBAC (Vaginal Birth After Cesarean) : Vaginal delivery for a women who have previously had C-section.",false));
        list.add(new Recyclemodel("Can I have sex during pregnancy?","Yes, it is safe to have sex during pregnancy. But , Be sure to prioritize safe sex practices",false));
        list.add(new Recyclemodel("Is it normal to be short of breath?","Yes! It is normal to experience shortness of breath during pregnancy due to hormonal changes, increased oxygen demand, increased blood volume and growing uterus putting pressure on the diaphragm.",false));
        list.add(new Recyclemodel("Is an ultrasound the only way to find out the baby's sex?","No!  \n" +
                "Ultrasound : It uses high-frequency sound waves to create images of the baby inside the womb. It is most common and reliable method that accurately determines baby's sex.\n"+"Other ways :-\n" +
                "Non-medical methods \n" +
                "Non-invasive prenatal testing (NIPT) \n" +
                "Amniocentesis \n",false));
        list.add(new Recyclemodel("How much weight should I gain during pregnancy?","Underweight(BMI < 18.5) : 12.5 kg - 18 kg\n" +
                "Normal Weight(BMI 18.5 - 24.9) : 11.5 kg - 16 kg\n" +
                "Overweight(BMI 25.0 - 29.9)\t: 7 kg - 11.5 kg\n" +
                "Obese(BMI >= 30.0) : 5 kg - 9 kg",false));


        adapter = new Adapter(list, faq.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigationview);
        bottomNavigationView.setSelectedItemId(R.id.bottomfaq);

        bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.bottomfaq:
                    return true;
                case R.id.bottomexplore:
                    startActivity(new Intent(getApplicationContext(),explore.class));
                    finish();
                    return true;
                case R.id.bottomhome:
                    startActivity(new Intent(getApplicationContext(),home.class));
                    finish();
                    return true;
                case R.id.bottomprof:
                    startActivity(new Intent(getApplicationContext(),patientprofile.class));
                    finish();
                    return true;
                case R.id.bottomcal:
                    startActivity(new Intent(getApplicationContext(),cal.class));
                    finish();
                    return true;
            }
            return false;
        } );


    }
}