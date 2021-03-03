package com.example.calmsleep.dialog

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.calmsleep.broadcast.BroadcastCheck
import com.example.calmsleep.databinding.SettingAlarmBinding
import com.example.calmsleep.databinding.SettingBedTimeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class BedTimeSetting: BottomSheetDialogFragment()  {

    private lateinit var binding: SettingBedTimeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SettingBedTimeBinding.inflate(inflater, container,false)

        val calendar = Calendar.getInstance()
        val alarmManager =   context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, BroadcastCheck::class.java)
        intent.putExtra("alarm",2)
        val setting = 1
        binding.timePicker.setIs24HourView(true)
        binding.btnDone.setOnClickListener {
            calendar.set(Calendar.HOUR_OF_DAY, binding.timePicker.currentHour)
            calendar.set(Calendar.MINUTE, binding.timePicker.currentMinute)
            val gio = binding.timePicker.currentHour
            val phut = binding.timePicker.currentMinute
            val pendingIntent =
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            dismiss()
            setDataLocal(setting,gio,phut)
            Toast.makeText(context,"Thời gian ngủ:  $gio:$phut", Toast.LENGTH_SHORT).show()
            var s =""
            if (phut < 10){
                s = "$gio:0$phut"
            }else{
                s ="$gio:$phut"
            }
            binding.tvTimeSet.text = s
        }

        binding.ivClose.setOnClickListener {
            dismiss()
        }

        return binding.root
    }




    private fun getDataLocal() {
        val sharedPreferences: SharedPreferences =
            context!!.applicationContext.getSharedPreferences(
                "setting",
                Context.MODE_PRIVATE
            )

        val gio = sharedPreferences.getInt("gio",0)
        val phut = sharedPreferences.getInt("phut",0)
        var string = "$gio:$phut"
        if(phut < 10){
            string = "$gio:0$phut"
        }

    }

    private fun setDataLocal(i: Int,gio:Int,phut:Int) {
        val sharedPreferences: SharedPreferences =
            context!!.applicationContext.getSharedPreferences(
                "setting",
                Context.MODE_PRIVATE
            )
        val editor = sharedPreferences.edit()
        editor.putInt("myappsetting", i)
        editor.putInt("gio", gio)
        editor.putInt("phut", phut)
        editor.apply()
    }

}