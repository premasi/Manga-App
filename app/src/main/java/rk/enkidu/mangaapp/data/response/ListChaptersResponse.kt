package rk.enkidu.mangaapp.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ListChaptersResponse(

	@field:SerializedName("data")
	val data: ChapterData? = null,

	@field:SerializedName("statusText")
	val statusText: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class ChapterData(

	@field:SerializedName("series")
	val series: List<ChapterSeriesItem>,

	@field:SerializedName("count")
	val count: Int? = null
)

@Parcelize
data class ChapterSeriesItem(

	@field:SerializedName("ChapterOrder")
	val chapterOrder: Int? = null,

	@field:SerializedName("ChapterNextSlug")
	val chapterNextSlug: String? = null,

	@field:SerializedName("ScrapeDate")
	val scrapeDate: String? = null,

	@field:SerializedName("ChapterPrevSlug")
	val chapterPrevSlug: String? = null,

	@field:SerializedName("ChapterShortUrl")
	val chapterShortUrl: String? = null,

	@field:SerializedName("_type")
	val type: String? = null,

	@field:SerializedName("ChapterTitle")
	val chapterTitle: String? = null,

	@field:SerializedName("ChapterNumber")
	val chapterNumber: String? = null,

	@field:SerializedName("ChapterUrl")
	val chapterUrl: String? = null,

	@field:SerializedName("_id")
	val id: String? = null,

	@field:SerializedName("ChapterContent")
	val chapterContent: List<String?>? = null,

	@field:SerializedName("ChapterDate")
	val chapterDate: String? = null
) : Parcelable
