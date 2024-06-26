package com.example.cm_lab_2.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cm_lab_2.R
import com.example.cm_lab_2.data.entities.Note
import com.example.cm_lab_2.data.vm.NoteViewModel
import java.text.SimpleDateFormat
import java.util.Date

class AddFragment : Fragment() {
    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        ViewModelProvider(this)[NoteViewModel::class.java].also { this.mNoteViewModel = it }

        val button = view.findViewById<Button>(R.id.save)
        button.setOnClickListener {
            addNote()
        }

        val backButton = view.findViewById<Button>(R.id.backToList)
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }

        return view
    }

    private fun addNote() {
        val noteText = view?.findViewById<EditText>(R.id.addNote)?.text.toString()
        val descriptionText = view?.findViewById<EditText>(R.id.addDescription)?.text.toString()

        if(noteText.isEmpty()) {
            Toast.makeText(view?.context, context?.getString(R.string.empty_note), Toast.LENGTH_LONG).show()
        }
        else {
            val currentTimeMillis = System.currentTimeMillis()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val formattedDate = dateFormat.format(Date(currentTimeMillis))
            val note = Note(0, noteText, descriptionText, formattedDate)

            mNoteViewModel.addNote(note)

            Toast.makeText(requireContext(), context?.getString(R.string.recorded_successfully), Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
    }
}