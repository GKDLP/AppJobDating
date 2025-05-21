package com.example.testbdd.api

import com.example.testbdd.model.DetailEntreprise
import com.example.testbdd.model.CreateDetailEntreprise
import com.example.testbdd.model.Entreprise
import com.example.testbdd.model.Disponibilite
import com.example.testbdd.model.Candidat
import retrofit2.Response
import retrofit2.http.*

interface SupabaseApi {
    @GET("rest/v1/entreprises")
    suspend fun getEntreprises(): Response<List<Entreprise>>

    @GET("rest/v1/liste1")
    suspend fun getDetailsEntrepriseByEntreprise(
        @Query("id_entreprises") idEntreprise: String,
        @Query("select") select: String = "*"
    ): Response<List<DetailEntreprise>>

    @GET("rest/v1/candidats")
    suspend fun getCandidats(
        @Query("id") id: String,
        @Query("select") select: String = "id,nom,prenom"
    ): Response<List<Candidat>>

    @GET("rest/v1/disponibilites")
    suspend fun getDisponibilitesEntreprise(
        @Query("id_entreprises") idEntreprise: Int,
        @Query("select") select: String = "*"
    ): Response<List<Disponibilite>>

    @POST("rest/v1/liste1")
    @Headers("Prefer: return=representation")
    suspend fun createDetailEntreprise(
        @Body detail: CreateDetailEntreprise
    ): Response<List<DetailEntreprise>>

    @PATCH("rest/v1/liste1")
    suspend fun updateDetailEntreprise(
        @Query("id") id: Int,
        @Body detail: DetailEntreprise
    ): Response<DetailEntreprise>
} 