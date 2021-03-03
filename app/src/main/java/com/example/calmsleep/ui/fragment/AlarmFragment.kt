package com.example.calmsleep.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.calmsleep.acivity.MainActivity
import com.example.calmsleep.application.MyApp
import com.example.calmsleep.databinding.FragmentAlarmBinding
import com.example.calmsleep.databinding.SettingLanguageBinding
import com.example.calmsleep.dialog.*
import com.example.calmsleep.model.MusicData

class AlarmFragment : Fragment(){
    private lateinit var binding: FragmentAlarmBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlarmBinding.inflate(inflater, container,false)
        val a = AlarmSetting()
        val b = BedTimeSetting()
        val c = NotificationPopupSetting()
        val d = PayDevSetting()
        val e = LanguageSetting()
        binding.cvAlarm.setOnClickListener {

            a.show(childFragmentManager,a.tag)
        }

        binding.cvBedTime.setOnClickListener {
            b.show(childFragmentManager,b.tag)
        }

        binding.cvNotification.setOnClickListener {
            c.show(childFragmentManager,c.tag)
        }
        binding.cvPayDeveloper.setOnClickListener {
            d.show(childFragmentManager,d.tag)
        }
        binding.cvAppLanguage.setOnClickListener {
            e.show(childFragmentManager,e.tag)
        }
        binding.cvRateUs.setOnClickListener {
            Toast.makeText(context,"Rate Us",Toast.LENGTH_SHORT).show()
        }

        binding.ivFavourites.setOnClickListener {
            val data = mutableListOf<MusicData>()
            for (i in MyApp.getMD()){
                for (j in MyApp.getFavourites()){
                   if ( i.id == j.musicId){
                       data.add(i)
                   }
                }

            }
            (activity as MainActivity).callDialogChill("Favourites",data)
        }

        binding.ivDownload.setOnClickListener {
            (activity as MainActivity).callDialogChill("Download",MyApp.getMusicDownLoad())
        }



        return binding.root
    }
}