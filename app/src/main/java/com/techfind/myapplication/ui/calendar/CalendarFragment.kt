package com.techfind.myapplication.ui.calendar

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.CalendarContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.techfind.myapplication.databinding.CalendarFragmentBinding

class CalendarFragment : Fragment() {

    private lateinit var calendarBinding: CalendarFragmentBinding
    private lateinit var calendarViewModel: CalendarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        calendarViewModel =
            ViewModelProvider(this).get(CalendarViewModel::class.java)

        calendarBinding = CalendarFragmentBinding.inflate(inflater, container, false)
        val root: View = calendarBinding.root

        val textView: TextView = calendarBinding.textCalendar
        calendarViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(calendarBinding) {
            setEvent.setOnClickListener {
                val service = serviceNameEditText.text.toString()
                val location = locationEditText.text.toString()
                val city = cityEditText.text.toString()
                val intent = Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.Events.TITLE,service)
                    .putExtra(CalendarContract.Events.EVENT_LOCATION,location.plus(", ").plus(city))
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,System.currentTimeMillis())
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME,System.currentTimeMillis()+(60*60*1000))
            startActivity(intent)
            }
        }
    }
}

