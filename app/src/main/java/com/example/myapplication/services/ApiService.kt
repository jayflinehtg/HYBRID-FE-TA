package com.example.myapplication.services

import com.example.myapplication.data.DataClassResponses
import com.example.myapplication.data.DataClassResponses.AverageRatingResponse
import com.example.myapplication.data.DataClassResponses.ConfirmAddPlantRequest
import com.example.myapplication.data.DataClassResponses.ConfirmEditPlantRequest
import com.example.myapplication.data.DataClassResponses.ConfirmPlantResponse
import com.example.myapplication.data.DataClassResponses.LoginApiResponse
import com.example.myapplication.data.DataClassResponses.LogoutResponse
import com.example.myapplication.data.DataClassResponses.PrepareRegistrationRequest
import com.example.myapplication.data.DataClassResponses.UserInfoResponse
import com.example.myapplication.data.IPFSResponse
import com.example.myapplication.data.LoginRequest
import com.example.myapplication.data.PaginatedPlantResponse
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiService {

    /* ================================ Autentikasi ================================ */

    @POST("auth/register")
    suspend fun prepareRegistration(
        @Body request: PrepareRegistrationRequest
    ): DataClassResponses.PrepareTransactionApiResponse

    @POST("auth/login")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): LoginApiResponse

    @GET("auth/user/{walletAddress}")
    suspend fun getUserInfo(@Path("walletAddress") walletAddress: String): UserInfoResponse

    @POST("auth/logout")
    suspend fun logoutUserFromServer(
        @Header("Authorization") authorization: String
    ): LogoutResponse

    /* ================================ Tanaman ================================ */
    @POST("plants/add")
    suspend fun prepareAddPlant(
        @Header("Authorization") token: String,
        @Body request: DataClassResponses.AddPlantRequest
    ): DataClassResponses.PrepareTransactionApiResponse

    @PUT("plants/edit/{plantId}")
    suspend fun prepareEditPlant(
        @Header("Authorization") token: String,
        @Path("plantId") plantId: String,
        @Body request: DataClassResponses.EditPlantRequest
    ): DataClassResponses.PrepareTransactionApiResponse

    @POST("plants/like")
    suspend fun prepareLikePlant(
        @Header("Authorization") token: String,
        @Body request: DataClassResponses.LikeRequest
    ): DataClassResponses.PrepareTransactionApiResponse

    @POST("plants/rate")
    suspend fun prepareRatePlant(
        @Header("Authorization") token: String,
        @Body request: DataClassResponses.RatePlantRequest
    ): DataClassResponses.PrepareTransactionApiResponse

    @POST("plants/comment")
    suspend fun prepareCommentPlant(
        @Header("Authorization") token: String,
        @Body request: DataClassResponses.CommentRequest
    ): DataClassResponses.PrepareTransactionApiResponse

    @GET("plants/all")
    suspend fun getPaginatedPlants(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 10
    ): PaginatedPlantResponse

    @GET("plants/{plantId}")
    suspend fun getPlantById(
        @Path("plantId") plantId: String,
        @Header("Authorization") token: String? = null
    ): DataClassResponses.GetPlantByIdResponse

    @GET("plants/search")
    suspend fun searchPlants(
        @Query("name") name: String = "",
        @Query("namaLatin") namaLatin: String = "",
        @Query("komposisi") komposisi: String = "",
        @Query("manfaat") manfaat: String = ""
    ): DataClassResponses.SearchPlantsResponse

    @GET("plants/averageRating/{plantId}")
    suspend fun getAverageRating(
        @Path("plantId") plantId: String
    ): AverageRatingResponse

    @GET("plants/{plantId}/comments")
    suspend fun getPaginatedComments(
        @Path("plantId") plantId: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int = 10
    ): DataClassResponses.PaginatedCommentResponse

    /* ================================ IPFS ================================ */
    @Multipart
    @POST("ipfs/upload")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): IPFSResponse

    @GET("ipfs/getFile/{cid}")
    suspend fun getFileFromIPFS(
        @Path("cid") cid: String
    ): ResponseBody

    /* ================================ TRANSACTION HISTORY ================================ */
    @GET("plants/public/record/{recordId}")
    suspend fun getPlantRecord(
        @Path("recordId") recordId: String
    ): DataClassResponses.PlantRecordResponse

    @GET("plants/public/records")
    suspend fun getAllPlantRecords(): DataClassResponses.AllPlantRecordsResponse

    @GET("plants/public/history/{plantId}")
    suspend fun getPlantTransactionHistory(
        @Path("plantId") plantId: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): DataClassResponses.TransactionHistoryResponse

    @GET("plants/public/count")
    suspend fun getRecordCount(): DataClassResponses.RecordCountResponse

    /* ================================ CONFIRMATION ENDPOINTS ================================ */
    @POST("plants/confirm-add")
    suspend fun confirmAddPlant(
        @Body request: ConfirmAddPlantRequest
    ): ConfirmPlantResponse

    @POST("plants/confirm-edit")
    suspend fun confirmEditPlant(
        @Body request: ConfirmEditPlantRequest
    ): ConfirmPlantResponse
}
