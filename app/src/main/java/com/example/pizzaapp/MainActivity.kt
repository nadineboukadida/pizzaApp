package com.example.pizzaapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    lateinit var nomText: TextInputEditText
    lateinit var prenomText: TextInputEditText
    lateinit var adresseText: TextInputEditText
    lateinit var pizzaType: AutoCompleteTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nomText = findViewById(R.id.nomlayout);
        prenomText = findViewById(R.id.prenomlayout);
        adresseText = findViewById(R.id.adresselayout);
        pizzaType = findViewById(R.id.autoCompleteTextView);

        val pizzaTypes = resources.getStringArray(R.array.pizzaTypes);
        val arrayAdapter = ArrayAdapter(this, R.layout.activity_dropdown, pizzaTypes);
        val dropdown = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        dropdown.setAdapter(arrayAdapter)

    }

    fun order(view: View) {
        if (view?.id == R.id.textButton) {
            var nom = nomText.text.toString();
            var prenom = prenomText.text.toString();
            var adresse = adresseText.text.toString();
            var pizza = pizzaType.text.toString();
            val text = "Merci de commander !";
            val order = "Merci pour votre commande $nom $prenom  , votre $pizza est en route vers $adresse";
            val duration = Toast.LENGTH_SHORT;
            val toast = Toast.makeText(applicationContext, text, duration);
            toast.show();
            val intent = Intent(view.context, ThanksPage::class.java);
            intent.putExtra("order", order);
            startActivity(intent);

            val intentMail = Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, "nadineboukadida01@gmail.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, order);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intentMail);
            }

        }
    }
}