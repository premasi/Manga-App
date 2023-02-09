package rk.enkidu.mangaapp.data.response

import com.google.gson.annotations.SerializedName

data class DetailSeriesResponse(

	@field:SerializedName("data")
	val data: DetailData,

	@field:SerializedName("statusText")
	val statusText: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DetailData(

	@field:SerializedName("MangaCover")
	val mangaCover: String? = null,

	@field:SerializedName("ScrapeDate")
	val scrapeDate: String? = null,

	@field:SerializedName("_type")
	val type: String? = null,

	@field:SerializedName("MangaSynopsis")
	val mangaSynopsis: String? = null,

	@field:SerializedName("MangaTitle")
	val mangaTitle: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("MangaShortUrl")
	val mangaShortUrl: String? = null,

	@field:SerializedName("MangaUrl")
	val mangaUrl: String? = null
)
