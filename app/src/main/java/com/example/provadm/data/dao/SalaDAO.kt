package com.example.provadm.data.dao

import androidx.room.*
import com.example.provadm.data.entity.SalaEntity


@Dao
interface SalaDAO {

    @Insert
    fun insert(SalaEntity: SalaEntity)

    @Update
    fun update(SalaEntity: SalaEntity)

    @Query("SELECT * FROM Sala WHERE professor = 0")
    fun selectAlunos(): List<SalaEntity>

    @Query("SELECT * FROM Sala WHERE nome = :nome AND perfil = :perfil")
    fun login(nome: String, perfil: String): SalaEntity?

    @Query("SELECT * FROM Sala WHERE id = :id")
    fun getPorID(id: Int): SalaEntity?

    @Query("DELETE FROM Sala WHERE id = :id")
    fun deletePorID(id: Int)

    @Query("SELECT Nome FROM Sala WHERE Nome = :nome")
    fun verificaUsuario(nome: String): String

}