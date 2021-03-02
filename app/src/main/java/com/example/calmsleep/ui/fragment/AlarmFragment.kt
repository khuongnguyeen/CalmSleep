package com.example.calmsleep.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.calmsleep.databinding.FragmentAlarmBinding
import com.example.calmsleep.databinding.SettingLanguageBinding
import com.example.calmsleep.dialog.*

class AlarmFragment : Fragment(){
    private lateinit var binding: FragmentAlarmBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlarmBinding.inflate(inflater, container,false)

        binding.cvAlarm.setOnClickListener {
            val v = AlarmSetting()
            v.show(childFragmentManager,v.tag)
        }

        binding.cvBedTime.setOnClickListener {
            val v = BedTimeSetting()
            v.show(childFragmentManager,v.tag)
        }

        binding.cvNotification.setOnClickListener {
            val v = NotificationPopupSetting()
            v.show(childFragmentManager,v.tag)
        }
        binding.cvPayDeveloper.setOnClickListener {
            val v = PayDevSetting()
            v.show(childFragmentManager,v.tag)
        }
        binding.cvAppLanguage.setOnClickListener {
            val v = LanguageSetting()
            v.show(childFragmentManager,v.tag)
        }
        binding.cvRateUs.setOnClickListener {
            Toast.makeText(context,"Rate Us",Toast.LENGTH_SHORT).show()
        }



        return binding.root
    }
}