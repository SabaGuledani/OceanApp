package com.example.oceanapp

import android.content.ContentValues
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.oceanapp.databinding.FragmentMapsBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MapsFragment : Fragment(R.layout.fragment_maps){

    private lateinit var googleMap: GoogleMap
    private lateinit var dbHelper: SQLiteOpenHelper.MyDBHelper
    private lateinit var binding: FragmentMapsBinding

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_maps, container, false)
//    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapsBinding.bind(view)
        dbHelper = SQLiteOpenHelper.MyDBHelper(requireContext())
        val db = dbHelper.writableDatabase

        val standardBottomSheetBehavior = BottomSheetBehavior.from(binding.standardBottomSheet)
        standardBottomSheetBehavior.saveFlags = BottomSheetBehavior.SAVE_ALL
        standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        val callback = OnMapReadyCallback { googleMap ->


//        standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN



            val cursor = db.rawQuery("SELECT * FROM lakes",null)
            while (cursor.moveToNext()){
                var i = 0
                val name = cursor.getString(1)

                val markerOptions = MarkerOptions()
                    .position(LatLng(cursor.getDouble(2), cursor.getDouble(3))) // Set the marker's position (latitude and longitude)
                    .title(cursor.getString(1)) // Set the title of the marker (optional)


                googleMap.addMarker(markerOptions)
                googleMap.setOnMarkerClickListener{clikedMarker->
                    
                    standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                    true
                }

            }
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)








        }


    }

