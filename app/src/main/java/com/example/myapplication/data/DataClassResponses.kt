package com.example.myapplication.data

import com.google.gson.annotations.SerializedName

class DataClassResponses {

    data class TransactionData(
        @SerializedName("transactionData") val transactionData: String
    )

    data class PrepareTransactionApiResponse(
        @SerializedName("success") val success: Boolean,
        @SerializedName("message") val message: String?,
        @SerializedName("txHash") val txHash: TransactionData? = null,
        @SerializedName("data") val data: TransactionData? = null,
        @SerializedName("transactionData") val transactionData: String? = null
    )

    data class PrepareRegistrationRequest(
        @SerializedName("fullName") val fullName: String,
        @SerializedName("password") val password: String,
        @SerializedName("walletAddress") val walletAddress: String

    )

    // Data class untuk respons login dari middleware (setelah verifikasi password)
    data class LoginApiResponse(
        @SerializedName("success") val success: Boolean,
        @SerializedName("token") val token: String?,
        @SerializedName("userData") val userData: UserData?,
        @SerializedName("loginTransactionData") val loginTransactionData: String?, // encodedABI untuk login on-chain
        @SerializedName("message") val message: String?
    )

    data class LogoutResponse(
        @SerializedName("success") val success: Boolean,
        @SerializedName("message") val message: String,
        @SerializedName("logoutTransactionData") val logoutTransactionData: String? = null,
        @SerializedName("publicKey") val publicKey: String? = null
    )

    data class UserData(
        @SerializedName("fullName") val fullName: String?,
        @SerializedName("isRegistered") val isRegistered: Boolean,
        @SerializedName("isLoggedIn") val isLoggedIn: Boolean
    )

    data class UserInfoResponse(
        @SerializedName("success") val success: Boolean,
        @SerializedName("userData") val userData: UserData
    )

    // Komentar dari pengguna
    data class Comment(
        @SerializedName("publicKey") val publicKey: String,
        @SerializedName("fullName") val fullName: String,
        @SerializedName("comment") val comment: String,
        @SerializedName("timestamp") val timestamp: String
    )

    // Respons berpaginasi untuk daftar komentar
    data class PaginatedCommentResponse(
        @SerializedName("success") val success: Boolean,
        @SerializedName("total") val total: Int,
        @SerializedName("currentPage") val currentPage: Int,
        @SerializedName("pageSize") val pageSize: Int,
        @SerializedName("totalPages") val totalPages: Int,
        @SerializedName("comments") val comments: List<Comment>
    )

    data class GetPlantByIdResponse(
        val success: Boolean,
        val plant: PlantResponse
    )

    // Data Class untuk AddPlant Request
    data class AddPlantRequest(
        @SerializedName("name") val name: String,
        @SerializedName("namaLatin") val namaLatin: String,
        @SerializedName("komposisi") val komposisi: String,
        @SerializedName("manfaat") val manfaat: String,
        @SerializedName("dosis") val dosis: String,
        @SerializedName("caraPengolahan") val caraPengolahan: String,
        @SerializedName("efekSamping") val efekSamping: String,
        @SerializedName("ipfsHash") val ipfsHash: String
    )

    // Data Class untuk EditPlant Request
    data class EditPlantRequest(
        @SerializedName("plantId") val plantId: String,
        @SerializedName("name") val name: String,
        @SerializedName("namaLatin") val namaLatin: String,
        @SerializedName("komposisi") val komposisi: String,
        @SerializedName("manfaat") val manfaat: String,
        @SerializedName("dosis") val dosis: String,
        @SerializedName("caraPengolahan") val caraPengolahan: String,
        @SerializedName("efekSamping") val efekSamping: String,
        @SerializedName("ipfsHash") val ipfsHash: String
    )

    data class SearchPlantsResponse(
        @SerializedName("success") val success: Boolean,
        @SerializedName("plants") val plants: List<PlantResponse>
    )

    data class AverageRatingResponse(
        @SerializedName("success") val success: Boolean,
        @SerializedName("averageRating") val averageRating: Double
    )

    data class RatePlantRequest(
        @SerializedName("plantId") val plantId: String,
        @SerializedName("rating") val rating: Int
    )

    data class RatedPlant(
        val plant: PlantResponse,
        val averageRating: Double
    )

    // Request untuk memberi like
    data class LikeRequest(
        @SerializedName("plantId") val plantId: String
    )

    // Request untuk komentar
    data class CommentRequest(
        @SerializedName("plantId") val plantId: String,
        @SerializedName("comment") val comment: String
    )

    /* ============================ RECORD TANAMAN =========================== */
    // Data class untuk Plant Record
    data class PlantRecord(
        @SerializedName("recordId") val recordId: String,
        @SerializedName("privateTxHash") val privateTxHash: String,
        @SerializedName("plantId") val plantId: String,
        @SerializedName("userAddress") val userAddress: String,
        @SerializedName("timestamp") val timestamp: String
    )

    // Data class untuk Transaction History Item
    data class TransactionHistoryItem(
        @SerializedName("recordId") val recordId: String,
        @SerializedName("privateTxHash") val privateTxHash: String,
        @SerializedName("plantId") val plantId: String,
        @SerializedName("userAddress") val userAddress: String,
        @SerializedName("timestamp") val timestamp: String,
        @SerializedName("transactionType") val transactionType: String,
        @SerializedName("icon") val icon: String,
        @SerializedName("formattedTimestamp") val formattedTimestamp: String
    )

    // Data class untuk Pagination
    data class Pagination(
        @SerializedName("currentPage") val currentPage: Int,
        @SerializedName("totalPages") val totalPages: Int,
        @SerializedName("totalRecords") val totalRecords: Int,
        @SerializedName("hasNextPage") val hasNextPage: Boolean,
        @SerializedName("hasPreviousPage") val hasPreviousPage: Boolean
    )

    // Response untuk single plant record
    data class PlantRecordResponse(
        @SerializedName("success") val success: Boolean,
        @SerializedName("record") val record: PlantRecord
    )

    // Response untuk all plant records
    data class AllPlantRecordsResponse(
        @SerializedName("success") val success: Boolean,
        @SerializedName("totalRecords") val totalRecords: Int,
        @SerializedName("records") val records: List<PlantRecord>
    )

    // Response untuk transaction history dengan pagination
    data class TransactionHistoryResponse(
        @SerializedName("success") val success: Boolean,
        @SerializedName("plantId") val plantId: String,
        @SerializedName("data") val data: List<TransactionHistoryItem>,
        @SerializedName("pagination") val pagination: Pagination
    )

    // Response untuk record count
    data class RecordCountResponse(
        @SerializedName("success") val success: Boolean,
        @SerializedName("recordCount") val recordCount: String
    )

    /* ================================ CONFIRMATION REQUESTS ================================ */
    data class ConfirmAddPlantRequest(
        @SerializedName("privateTxHash") val privateTxHash: String,
        @SerializedName("plantId") val plantId: String,
        @SerializedName("userAddress") val userAddress: String
    )

    data class ConfirmEditPlantRequest(
        @SerializedName("privateTxHash") val privateTxHash: String,
        @SerializedName("plantId") val plantId: String,
        @SerializedName("userAddress") val userAddress: String
    )

    /* ================================ CONFIRMATION RESPONSE ================================ */
    data class ConfirmPlantResponse(
        @SerializedName("success") val success: Boolean,
        @SerializedName("message") val message: String,
        @SerializedName("besu") val besu: BesuTransactionInfo? = null,
        @SerializedName("public") val public: PublicTransactionInfo? = null,
        @SerializedName("publicError") val publicError: String? = null,
        @SerializedName("warning") val warning: String? = null
    )

    data class BesuTransactionInfo(
        @SerializedName("txHash") val txHash: String,
        @SerializedName("plantId") val plantId: String,
    )

    data class PublicTransactionInfo(
        @SerializedName("txHash") val txHash: String,
        @SerializedName("updateTxHash") val updateTxHash: String? = null,
        @SerializedName("recordId") val recordId: String? = null,
        @SerializedName("blockNumber") val blockNumber: String,
        @SerializedName("gasUsed") val gasUsed: String
    )
}