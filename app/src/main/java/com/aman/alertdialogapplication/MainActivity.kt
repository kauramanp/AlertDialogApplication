package com.aman.alertdialogapplication

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.aman.alertdialogapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: Adapter
    val animals = arrayOf("Horse", "Cow", "Camel", "Sheep", "Goat")
    val checkedItems = booleanArrayOf(true, false, false, true, false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNextCustomActivity.setOnClickListener {
            var intent = Intent(this, CustomDialogActivity::class.java)
            startActivity(intent)
        }

        binding.btnAlertDialog.setOnClickListener {
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(resources.getString(R.string.simple_alert_dialog_with_title_message))
            alertDialog.setMessage(resources.getString(R.string.message))
            alertDialog.setPositiveButton(resources.getString(R.string.positive_button)) { _, _ ->
                Toast.makeText(
                    this,
                    resources.getString(R.string.positive_button_clicked),
                    Toast.LENGTH_LONG
                ).show()
            }
            alertDialog.setNegativeButton(resources.getString(R.string.negative_button)) { _, _ ->
                Toast.makeText(
                    this,
                    resources.getString(R.string.negative_button_clicked),
                    Toast.LENGTH_LONG
                ).show()
            }
            alertDialog.setNeutralButton(resources.getString(R.string.neutral_button)) { _, _ ->
                Toast.makeText(
                    this,
                    resources.getString(R.string.neutral_button_clicked),
                    Toast.LENGTH_LONG
                ).show()
            }
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
        binding.btnSingleChooiceDialog.setOnClickListener {
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(resources.getString(R.string.alert_dialog_with_single_choice))
            alertDialog.setItems(animals) { _, which ->
                when (which) {
                    0 -> {
                        Toast.makeText(
                            this,
                            resources.getString(R.string.horse_clicked),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    1 -> {
                        Toast.makeText(
                            this,
                            resources.getString(R.string.cow_clicked),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    2 -> {
                        Toast.makeText(
                            this,
                            resources.getString(R.string.camel_clicked),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    3 -> {
                        Toast.makeText(
                            this,
                            resources.getString(R.string.sheep_clicked),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    4 -> {
                        Toast.makeText(
                            this,
                            resources.getString(R.string.goat_clicked),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            alertDialog.setPositiveButton(resources.getString(R.string.positive_button)) { _, _ ->
                Toast.makeText(
                    this,
                    resources.getString(R.string.positive_button_clicked),
                    Toast.LENGTH_LONG
                ).show()
            }
            alertDialog.setNegativeButton(resources.getString(R.string.negative_button)) { _, _ ->
                Toast.makeText(
                    this,
                    resources.getString(R.string.negative_button_clicked),
                    Toast.LENGTH_LONG
                ).show()
            }
            alertDialog.setNeutralButton(resources.getString(R.string.neutral_button)) { _, _ ->
                Toast.makeText(
                    this,
                    resources.getString(R.string.neutral_button_clicked),
                    Toast.LENGTH_LONG
                ).show()
            }
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
        binding.btnMultipleChooiceDialog.setOnClickListener {
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(resources.getString(R.string.alert_dialog_with_multiple_choice))
            alertDialog.setMultiChoiceItems(animals, checkedItems
            ) { _, which, isChecked ->
                checkedItems.set(which, isChecked)
            }
            alertDialog.setPositiveButton(resources.getString(R.string.positive_button)) { _, _ ->
                Toast.makeText(
                    this,
                    resources.getString(R.string.positive_button_clicked),
                    Toast.LENGTH_LONG
                ).show()
            }
            alertDialog.setNegativeButton(resources.getString(R.string.negative_button)) { _, _ ->
                Toast.makeText(
                    this,
                    resources.getString(R.string.negative_button_clicked),
                    Toast.LENGTH_LONG
                ).show()
            }
            alertDialog.setNeutralButton(resources.getString(R.string.neutral_button)) { _, _ ->
                Toast.makeText(
                    this,
                    resources.getString(R.string.neutral_button_clicked),
                    Toast.LENGTH_LONG
                ).show()
            }
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

        binding.btnCustomDialog.setOnClickListener {
            var dialogView = LayoutInflater.from(this).inflate(R.layout.layout_dialog, null)
            var dialog = Dialog(this@MainActivity)
            dialog.setContentView(dialogView)
            var btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)
            var btnOk = dialogView.findViewById<Button>(R.id.btnOk)
            var etText = dialogView.findViewById<EditText>(R.id.etText)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
            dialog.setCancelable(false)
            btnCancel.setOnClickListener {
                dialog.dismiss()
            }
            btnOk.setOnClickListener {
                if (etText.text.isNullOrEmpty()) {
                    Toast.makeText(
                        this,
                        resources.getString(R.string.enter_value),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(this, etText.text.toString(), Toast.LENGTH_LONG)
                        .show()
                    dialog.dismiss()
                }
            }
            dialog.show()
        }

        binding.btnBottomSheet.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
    }
}

