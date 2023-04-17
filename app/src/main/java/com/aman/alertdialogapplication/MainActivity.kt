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
            AlertDialog.Builder(this).apply{
                setTitle(resources.getString(R.string.simple_alert_dialog_with_title_message))
                setMessage(resources.getString(R.string.message))
                setPositiveButton(resources.getString(R.string.positive_button)) { _, _ ->
                    Toast.makeText(
                        this@MainActivity,
                        resources.getString(R.string.positive_button_clicked),
                        Toast.LENGTH_LONG
                    ).show()
                }
                setNegativeButton(resources.getString(R.string.negative_button)) { _, _ ->
                    Toast.makeText(
                        this@MainActivity,
                        resources.getString(R.string.negative_button_clicked),
                        Toast.LENGTH_LONG
                    ).show()
                }
                setNeutralButton(resources.getString(R.string.neutral_button)) { _, _ ->
                    Toast.makeText(
                        this@MainActivity,
                        resources.getString(R.string.neutral_button_clicked),
                        Toast.LENGTH_LONG
                    ).show()
                }
                setCancelable(false)
                show()
            }
        }
        binding.btnSingleChooiceDialog.setOnClickListener {
            AlertDialog.Builder(this)
                .apply {
                    setTitle(resources.getString(R.string.alert_dialog_with_single_choice))
                    setItems(animals) { _, which ->
                        Toast.makeText(
                            this@MainActivity,
                            this@MainActivity.resources.getString(
                                R.string.item_clicked_dynamic,
                                animals[which].toString()
                            ),
                            Toast.LENGTH_LONG
                        ).show()

                    }
                    setPositiveButton(resources.getString(R.string.positive_button)) { _, _ ->
                        Toast.makeText(
                            this@MainActivity,
                            resources.getString(R.string.positive_button_clicked),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    setNegativeButton(resources.getString(R.string.negative_button)) { _, _ ->
                        Toast.makeText(
                            this@MainActivity,
                            resources.getString(R.string.negative_button_clicked),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    setNeutralButton(resources.getString(R.string.neutral_button)) { _, _ ->
                        Toast.makeText(
                            this@MainActivity,
                            resources.getString(R.string.neutral_button_clicked),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    setCancelable(false)
                    show()
                }
        }

        
        binding.btnMultipleChooiceDialog.setOnClickListener {
            AlertDialog.Builder(this).apply{
            setTitle(resources.getString(R.string.alert_dialog_with_multiple_choice))
            setMultiChoiceItems(
                animals, checkedItems
            ) { _, which, isChecked ->
                checkedItems.set(which, isChecked)
            }
            setPositiveButton(resources.getString(R.string.positive_button)) { _, _ ->
                Toast.makeText(
                    this@MainActivity,
                    resources.getString(R.string.positive_button_clicked),
                    Toast.LENGTH_LONG
                ).show()
            }
            setNegativeButton(resources.getString(R.string.negative_button)) { _, _ ->
                Toast.makeText(
                    this@MainActivity,
                    resources.getString(R.string.negative_button_clicked),
                    Toast.LENGTH_LONG
                ).show()
            }
            setNeutralButton(resources.getString(R.string.neutral_button)) { _, _ ->
                Toast.makeText(
                    this@MainActivity,
                    resources.getString(R.string.neutral_button_clicked),
                    Toast.LENGTH_LONG
                ).show()
            }
            setCancelable(false)
            show()
        }
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

