package com.example.provadm

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.provadm.databinding.FragmentAlunoBinding


class AlunoFragment : Fragment() {

    private lateinit var binding: FragmentAlunoBinding
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlunoBinding.inflate(inflater, container, false)
        navController = findNavController()
        var troca = 0

        binding.btnClick.setOnClickListener {
            if(troca == 0){
                binding.btnClick.setText("Tente agora!")
                troca = 1
            }
            else if(troca == 1){
                binding.btnClick.setText("Agora vai perdao!!")
                troca = 2
            }
            else if(troca== 2){
                binding.btnClick.setText("Sistema buggou vou resetar!!")
                troca = 0
            }
        }

        return binding.root
    }

}