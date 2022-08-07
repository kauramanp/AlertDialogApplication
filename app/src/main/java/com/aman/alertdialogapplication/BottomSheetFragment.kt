package com.aman.alertdialogapplication


import android.R
import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.aman.alertdialogapplication.databinding.BottomSheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetFragment : BottomSheetDialogFragment() {
    var bottomSheetBehavior: BottomSheetBehavior<*>? = null
    var binding: BottomSheetLayoutBinding? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheet = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        binding = BottomSheetLayoutBinding.inflate(layoutInflater)

        bottomSheet.setContentView(binding!!.root)
        bottomSheetBehavior = BottomSheetBehavior.from<View>(binding!!.root?.getParent() as View)

        (bottomSheetBehavior as BottomSheetBehavior<View>).setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO)

        binding!!.extraSpace.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels / 2)
        (bottomSheetBehavior as BottomSheetBehavior<View>).setBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(view: View, i: Int) {
                if (BottomSheetBehavior.STATE_EXPANDED == i) {
                    showView(binding!!.appBarLayout, actionBarSize)
                    hideAppBar(binding!!.profileLayout)
                }
                if (BottomSheetBehavior.STATE_COLLAPSED == i) {
                    hideAppBar(binding!!.appBarLayout)
                    showView(binding!!.profileLayout, actionBarSize)
                }
                if (BottomSheetBehavior.STATE_HIDDEN == i) {
                    dismiss()
                }
            }

            override fun onSlide(view: View, v: Float) {}
        })

        //aap bar cancel button clicked
        binding!!.cancelBtn.setOnClickListener{
            fun onClick(view: View?) {
                dismiss()
            }
        }

        hideAppBar(binding!!.appBarLayout)
        return bottomSheet
    }

    override fun onStart() {
        super.onStart()
        bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun hideAppBar(view: View) {
        val params: ViewGroup.LayoutParams = view.getLayoutParams()
        params.height = 0
        view.setLayoutParams(params)
    }

    private fun showView(view: View, size: Int) {
        val params: ViewGroup.LayoutParams = view.getLayoutParams()
        params.height = size
        view.setLayoutParams(params)
    }

    private val actionBarSize: Int
        private get() {
            val array =
                requireContext().theme.obtainStyledAttributes(intArrayOf(R.attr.actionBarSize))
            return array.getDimension(0, 0f).toInt()
        }
}