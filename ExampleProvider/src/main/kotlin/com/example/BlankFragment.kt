package com.example

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.TextViewCompat
import com.lagradost.cloudstream3.R
import com.lagradost.cloudstream3.databinding.FragmentBlankBinding
import com.lagradost.cloudstream3.ui.BaseBottomSheetDialogFragment
import com.lagradost.cloudstream3.ui.BaseFragment
import com.lagradost.cloudstream3.utils.UIHelper.colorFromAttribute

/**
 * A simple [BaseBottomSheetDialogFragment] subclass.
 */
class BlankFragment(private val plugin: ExamplePlugin) : BaseBottomSheetDialogFragment<FragmentBlankBinding>(
    BaseFragment.BindingCreator.Inflate(FragmentBlankBinding::inflate)
) {

    // Helper function to get a drawable resource by name
    @SuppressLint("DiscouragedApi")
    @Suppress("SameParameterValue")
    private fun getDrawable(name: String): Drawable? {
        val id = plugin.resources?.getIdentifier(name, "drawable", BuildConfig.LIBRARY_PACKAGE_NAME)
        return id?.let { ResourcesCompat.getDrawable(plugin.resources ?: return null, it, null) }
    }

    // Helper function to get a string resource by name
    @SuppressLint("DiscouragedApi")
    @Suppress("SameParameterValue")
    private fun getStringRes(name: String): String? {
        val id = plugin.resources?.getIdentifier(name, "string", BuildConfig.LIBRARY_PACKAGE_NAME)
        return id?.let { plugin.resources?.getString(it) }
    }

    override fun fixPadding(view: View) {
        // Don't need any padding here
    }

    override fun onBindingCreated(binding: FragmentBlankBinding) {
        binding.apply {
            // Set text
            textView.text = getStringRes("hello_fragment")
            TextViewCompat.setTextAppearance(textView, R.style.ResultInfoText)

            textView2.text = root.context.resources.getText(R.string.legal_notice_text)

            // Set images
            imageView.setImageDrawable(getDrawable("ic_android_24dp"))
            imageView.imageTintList = ColorStateList.valueOf(root.context.getColor(R.color.white))

            imageView2.setImageDrawable(getDrawable("ic_android_24dp"))
            imageView2.imageTintList = ColorStateList.valueOf(root.context.colorFromAttribute(R.attr.white))
        }
    }
}
