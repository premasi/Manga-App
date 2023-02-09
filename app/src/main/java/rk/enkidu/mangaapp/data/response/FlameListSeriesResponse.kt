package rk.enkidu.mangaapp.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class FlameListSeriesResponse(

	@field:SerializedName("data")
	val data: FlameData? = null,

	@field:SerializedName("statusText")
	val statusText: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class FlameData(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("series")
	val series: List<FlameSeriesItem>,

	@field:SerializedName("count")
	val count: Int? = null
)

@Parcelize
data class FlameSeriesItem(

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
) : Parcelable
