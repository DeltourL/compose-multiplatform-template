package network.data

@kotlinx.serialization.Serializable
data class Answer(
    val id: Long,
    val label: String
) {}