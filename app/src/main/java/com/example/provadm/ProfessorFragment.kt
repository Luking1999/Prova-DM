package com.example.provadm

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.navigation.fragment.findNavController
import com.example.provadm.data.AppDataBase
import com.example.provadm.data.dao.SalaDAO
import com.example.provadm.databinding.FragmentProfessorBinding


class ProfessorFragment : Fragment() {

    private lateinit var binding: FragmentProfessorBinding
    private lateinit var db: AppDataBase
    private lateinit var salaDAO: SalaDAO
    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfessorBinding.inflate(inflater, container, false)
        db = AppDataBase.getInstance(requireContext())
        salaDAO = db.salaDAO()
        listView = binding.listViewID

        listar()

        return binding.root
    }

    fun listar() {
        var alunosList = salaDAO.selectAlunos()
        var nomeAlunos = mutableListOf<String>()
        var idAluno = mutableListOf<Int>()
        var contador = 0;

        for (aluno in alunosList) {
            contador++
            var lista = "${contador}º - Nome: ${aluno.nome}"
            nomeAlunos.add(lista)
            aluno.id?.let { idAluno.add(it) }
        }

        var context = activity?.baseContext as Context
        var resource = android.R.layout.simple_list_item_1

        var adapter: ArrayAdapter<String> = ArrayAdapter(context, resource, nomeAlunos)

        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, posicao, _ ->
            var id = salaDAO.getPorID(idAluno[posicao])
            var bundle = Bundle()
            var navegacao = findNavController()

            if (id != null) {
                bundle.putInt("alunoID", id.id!!)
            }

            navegacao.navigate(R.id.action_professorFragment_to_cadastroFragment, bundle)
        }
    }


}