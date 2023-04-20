package com.example.provadm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.provadm.data.AppDataBase
import com.example.provadm.data.dao.SalaDAO
import com.example.provadm.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController
    private lateinit var db: AppDataBase
    private lateinit var salaDAO: SalaDAO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        navController = findNavController()
        db = AppDataBase.getInstance(requireContext())
        salaDAO = db.salaDAO()

        var toast = Toast(requireContext())
        toast.setText("Usuario invalido!")

        binding.btnCadastrar.setOnClickListener { navController.navigate(R.id.action_homeFragment_to_cadastroFragment) }
        binding.btnEntrar.setOnClickListener {
            var usuario = salaDAO.login(binding.nomeLoginID.text.toString(), binding.perfilLoginID.text.toString())
            if(usuario != null && usuario.prof)
                navController.navigate(R.id.action_homeFragment_to_professorFragment)
            else if(usuario != null && !usuario.prof)
                navController.navigate(R.id.action_homeFragment_to_alunoFragment)
            else
                toast.show()
        }

        return binding.root
    }

}