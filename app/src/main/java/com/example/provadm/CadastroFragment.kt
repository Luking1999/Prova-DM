package com.example.provadm

import android.app.AlertDialog
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
import com.example.provadm.data.entity.SalaEntity
import com.example.provadm.databinding.FragmentCadastroBinding


class CadastroFragment : Fragment() {

    private lateinit var binding: FragmentCadastroBinding
    private lateinit var navController: NavController
    private lateinit var db: AppDataBase
    private lateinit var salaDAO: SalaDAO
    private var alunoID: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCadastroBinding.inflate(inflater, container, false)
        navController = findNavController()

        db = AppDataBase.getInstance(requireContext())
        salaDAO = db.salaDAO()

        arguments?.let {
            alunoID = it.getInt("alunoID", -1)
        }

        alunoID?.let {
            var aluno = salaDAO.getPorID(it)

            binding.nomeNovoUsuario.setText(aluno?.nome)
            binding.perfilNovoUsuario.setText(aluno?.perfil)

            var deletarID = 0
            aluno?.id?.let { deletarID = it }
            binding.btnDeletar.setOnClickListener { deletar(deletarID) }
        }

        binding.btnSalvar.setOnClickListener { salvar() }
        // Inflate the layout for this fragment
        return binding.root
    }

    fun deletar(id: Int){
        var navegacao = findNavController()
        var confirmacao = AlertDialog.Builder(requireContext())
        confirmacao.setTitle("Tem certeza que deseja remover?")
        confirmacao.setMessage("Ao clicar em \"Confirmar\" o aluno sera removido!")

        confirmacao.setPositiveButton("Confirmar"){_,_ ->
            salaDAO.deletePorID(id)
            navegacao.navigateUp()
        }
        confirmacao.setNegativeButton("Cancelar"){ caixa, _ ->
            caixa.dismiss()
        }

        confirmacao.show()

    }

    fun salvar() {
        var nome = binding.nomeNovoUsuario.text.toString()
        var perfil = binding.perfilNovoUsuario.text.toString()
        var validaUser = salaDAO.verificaUsuario(nome)
        var toast = Toast(requireContext())

        if(validaUser.isNullOrEmpty()) {
            if (nome.isNullOrEmpty() || perfil.isNullOrEmpty()) {
                toast.setText("Verifique se o nome e o perfil foram preenchidos corretamente por favor!")
                toast.show()
            } else {
                var validaProfessor = false
                if (perfil.lowercase() == "professor")
                    validaProfessor = true

                var novoUsuario = SalaEntity(null, nome, perfil, validaProfessor)
                salaDAO.insert(novoUsuario)
                navController.navigateUp()
            }
        }else{
            toast.setText("Já existe um usuario com este nome")
            toast.show()
        }
    }

}