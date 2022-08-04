package com.aman.alertdialogapplication

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.EditText
import android.widget.Toast
import com.aman.alertdialogapplication.databinding.ActivityCustomDialogBinding
import com.aman.alertdialogapplication.databinding.DialogLayoutBinding
import com.aman.alertdialogapplication.databinding.LayoutDialogBinding

class CustomDialogActivity : AppCompatActivity() {
    lateinit var binding: ActivityCustomDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpdate.setOnClickListener {
            var dialogBinding = DialogLayoutBinding.inflate(layoutInflater)

            var dialog = Dialog(this)
            dialog.setContentView(dialogBinding.root)
            dialog.window?.setLayout(MATCH_PARENT, WRAP_CONTENT)
            dialog.setCancelable(false)
            dialogBinding.etEnterName.setText(binding.tvEnterName.text)
            dialogBinding.etAddress.setText(binding.tvAddress.text)
            dialogBinding.etOtherName.setText(binding.tvOtherName.text)
            if(binding.tvOtherName.text.trim().isNullOrEmpty()){
                dialogBinding.etOtherName.visibility = View.INVISIBLE
            }

            when(binding.tvGender.text.toString().toLowerCase()){
                "male"-> dialogBinding.rbHe.isChecked = true
                "female"-> dialogBinding.rbshe.isChecked = true
                else-> {
                    dialogBinding.rbOther.isChecked = true
                    dialogBinding.etOtherName.visibility = View.VISIBLE
                }
            }

            dialogBinding.rg.setOnCheckedChangeListener { _, id ->
                dialogBinding.etOtherName.text.clear()
                when(id){
                    R.id.rbOther-> {
                        dialogBinding.etOtherName.visibility = View.VISIBLE
                        binding.tvOtherName.visibility = View.VISIBLE
                        binding.tvGender.setText("Others")
                        dialogBinding.etOtherName.text.clear()
                    }
                    R.id.rbHe->{
                        dialogBinding.etOtherName.visibility = View.INVISIBLE
                        binding.tvOtherName.visibility=View.INVISIBLE
                        binding.tvGender.setText("Male")
                    }
                    R.id.rbshe->{
                        dialogBinding.etOtherName.visibility = View.INVISIBLE
                        binding.tvOtherName.visibility=View.INVISIBLE
                        binding.tvGender.setText("Female")
                    }
                    else-> {
                        dialogBinding.etOtherName.visibility = View.INVISIBLE
                        binding.tvOtherName.visibility=View.INVISIBLE
                    }
                }
            }
            dialogBinding.btnUpdate.setOnClickListener {
                if(dialogBinding.etEnterName.text.isNullOrEmpty()){
                    Toast.makeText(this, "Enter name", Toast.LENGTH_LONG).show()
                    dialogBinding.etEnterName.requestFocus()
                }else if(dialogBinding.etAddress.text.isNullOrEmpty()){
                    Toast.makeText(this, "Enter address", Toast.LENGTH_LONG).show()
                    dialogBinding.etAddress.requestFocus()
                }else if(dialogBinding.etOtherName.text.isNullOrEmpty() && dialogBinding.rbOther.isChecked){
                    Toast.makeText(this, "Enter other name", Toast.LENGTH_LONG).show()
                    dialogBinding.etOtherName.requestFocus()
                }else{
                    binding.tvOtherName.setText(dialogBinding.etOtherName.text)
                    binding.tvEnterName.setText(dialogBinding.etEnterName.text)
                    binding.tvAddress.setText(dialogBinding.etAddress.text)
                    when(dialogBinding.rg.checkedRadioButtonId){
                        R.id.rbOther-> {
                            dialogBinding.etOtherName.visibility = View.VISIBLE
                            binding.tvOtherName.visibility = View.VISIBLE
                            binding.tvGender.setText("Others")
                            dialogBinding.etOtherName.text.clear()
                        }
                        R.id.rbHe->{
                            dialogBinding.etOtherName.visibility = View.INVISIBLE
                            binding.tvOtherName.visibility=View.INVISIBLE
                            binding.tvGender.setText("Male")
                        }
                        R.id.rbshe->{
                            dialogBinding.etOtherName.visibility = View.INVISIBLE
                            binding.tvOtherName.visibility=View.INVISIBLE
                            binding.tvGender.setText("Female")
                        }
                        else-> {
                            dialogBinding.etOtherName.visibility = View.INVISIBLE
                            binding.tvOtherName.visibility=View.INVISIBLE
                        }
                    }
                    dialog.dismiss()
                }
            }
            dialog.show()


        }
    }
}