package com.example.calmsleep.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Environment
import com.example.calmsleep.model.FavouriteData
import com.example.calmsleep.model.MusicData
import java.io.File
import java.io.FileOutputStream

class DataBaseManager(private var context: Context) {

    companion object {
        private const val DB_Name = "khuong_db"
        private const val name_table_1 = "music"

    }

    init {
        copyDatabase()
    }

    private var database: SQLiteDatabase? = null


    private fun copyDatabase() {
        val input = context.assets.open(DB_Name)
        val path =
            Environment.getDataDirectory().path + File.separator + "data" + File.separator + context.packageName + File.separator + "db"
        File(path).mkdir()
        val fullPath = path + File.separator + DB_Name
        if (File(fullPath).exists()) {
            return
        }
        val out = FileOutputStream(fullPath)
        val b = ByteArray(1024)
        var length = input.read(b)
        while (length >= 0) {
            out.write(b, 0, length)
            length = input.read(b)
        }
        input.close()
        out.close()
    }

    fun createFavourites() {
        val sql = "CREATE TABLE IF NOT EXISTS favourites ( " +
                "id INTEGER NOT NULL UNIQUE, " +
                "music_id INTEGER  NOT NULL, " +
                "album_id INTEGER  NOT NULL, " +
                "category_id INTEGER  NOT NULL, " +
                "PRIMARY KEY(id AUTOINCREMENT) " +
                ")"
        openDatabase()
        database?.execSQL(sql)
        closeDatabase()
    }

    fun deleteFavourite(musicId: Int) {
        val sql = "DELETE FROM favourites  WHERE music_id = $musicId"
        openDatabase()
        database?.execSQL(sql)
        closeDatabase()
    }



    fun insertFavourites(idMusic: Int, idAlbum: Int, idCategory: Int) {
        openDatabase()
        //ContentValues de chua cac cap gia tri keu(column name) + value (value of column)
        val contentValues = ContentValues()
        contentValues.put("music_id", idMusic)
        contentValues.put("album_id", idAlbum)
        contentValues.put("category_id", idCategory)
        database?.insert("favourites", null, contentValues)
        closeDatabase()
    }

    fun getFavourites(): MutableList<FavouriteData> {
        val favourite = mutableListOf<FavouriteData>()
        openDatabase()
        val sql = "SELECT * FROM favourites"
        val cursor = database!!.rawQuery(sql, null)
        cursor.moveToFirst()
        val indexId = cursor.getColumnIndex("id")
        val indexMusicId = cursor.getColumnIndex("music_id")
        val indexTAlbumId = cursor.getColumnIndex("album_id")
        val indexCategoryId = cursor.getColumnIndex("category_id")

        while (!cursor.isAfterLast) {
            val id = cursor.getInt(indexId)
            val musicId = cursor.getInt(indexMusicId)
            val albumId = cursor.getInt(indexTAlbumId)
            val categoryId = cursor.getInt(indexCategoryId)
            favourite.add(FavouriteData(id = id ,musicId = musicId,albumId = albumId,categoryId = categoryId))
            cursor.moveToNext()
        }
        cursor.close()
        closeDatabase()

        return favourite



    }

    fun getMusic(): MutableList<MusicData> {
        val musicData = mutableListOf<MusicData>()
        openDatabase()
        val sql = "SELECT * FROM music"
        val cursor = database!!.rawQuery(sql, null)
        cursor.moveToFirst()
        val indexId = cursor.getColumnIndex("id")
        val indexSongName = cursor.getColumnIndex("songName")
        val indexArtistName = cursor.getColumnIndex("artistName")
        val indexLinkSong = cursor.getColumnIndex("linkSong")
        val indexLinkImage = cursor.getColumnIndex("linkImage")
        val indexLinkMusic = cursor.getColumnIndex("linkMusic")
        val indexTAlbumId = cursor.getColumnIndex("albumid")
        val indexCategoryId = cursor.getColumnIndex("categoryid")
        while (!cursor.isAfterLast) {
            val id = cursor.getInt(indexId)
            val songName = cursor.getString(indexSongName)
            val artistName = cursor.getString(indexArtistName)
            val linkSong = cursor.getString(indexLinkSong)
            val linkImage = cursor.getString(indexLinkImage)
            val linkMusic = cursor.getString(indexLinkMusic)
            val albumid = cursor.getInt(indexTAlbumId)
            val categoryid = cursor.getInt(indexCategoryId)
            musicData.add(
                MusicData(
                    id = id,
                    songName = songName,
                    artistName = artistName,
                    linkSong = linkSong,
                    linkImage = linkImage,
                    linkMusic = linkMusic,
                    albumid = albumid,
                    categoryId = categoryid
                )
            )
            cursor.moveToNext()
        }
        cursor.close()
        closeDatabase()

        return musicData
    }


    fun getAlbumId(id: Int): String {
        openDatabase()
        val sql = "SELECT * FROM album WHERE id = $id"
        val cursor = database!!.rawQuery(sql, null)
        cursor.moveToFirst()
        val indexName = cursor.getColumnIndex("name")
        var s = ""
        while (!cursor.isAfterLast) {
            val songName = cursor.getString(indexName)
            s = songName
            cursor.moveToNext()
        }
        cursor.close()
        closeDatabase()

        return s
    }

    fun getCategoryId(id: Int): String {
        openDatabase()
        val sql = "SELECT * FROM category WHERE id = $id"
        val cursor = database!!.rawQuery(sql, null)
        cursor.moveToFirst()
        val indexName = cursor.getColumnIndex("name")
        var s = ""
        while (!cursor.isAfterLast) {
            val songName = cursor.getString(indexName)
            s = songName
            cursor.moveToNext()
        }
        cursor.close()
        closeDatabase()

        return s
    }

    fun getMusicAlbumId(id: Int): MutableList<MusicData> {
        val musicData = mutableListOf<MusicData>()
        openDatabase()
        val sql = "SELECT * FROM music WHERE albumid = $id"
        val cursor = database!!.rawQuery(sql, null)
        cursor.moveToFirst()
        val indexId = cursor.getColumnIndex("id")
        val indexSongName = cursor.getColumnIndex("songName")
        val indexArtistName = cursor.getColumnIndex("artistName")
        val indexLinkSong = cursor.getColumnIndex("linkSong")
        val indexLinkImage = cursor.getColumnIndex("linkImage")
        val indexLinkMusic = cursor.getColumnIndex("linkMusic")
        val indexTAlbumId = cursor.getColumnIndex("albumid")
        val indexCategoryId = cursor.getColumnIndex("categoryid")
        while (!cursor.isAfterLast) {
            val id = cursor.getInt(indexId)
            val songName = cursor.getString(indexSongName)
            val artistName = cursor.getString(indexArtistName)
            val linkSong = cursor.getString(indexLinkSong)
            val linkImage = cursor.getString(indexLinkImage)
            val linkMusic = cursor.getString(indexLinkMusic)
            val albumid = cursor.getInt(indexTAlbumId)
            val categoryid = cursor.getInt(indexCategoryId)
            musicData.add(
                MusicData(
                    id = id,
                    songName = songName,
                    artistName = artistName,
                    linkSong = linkSong,
                    linkImage = linkImage,
                    linkMusic = linkMusic,
                    albumid = albumid,
                    categoryId = categoryid
                )
            )
            cursor.moveToNext()
        }
        cursor.close()
        closeDatabase()

        return musicData
    }

    fun getMusic(id: Int): MusicData {
        openDatabase()
        val sql = "SELECT * FROM music WHERE id = $id"
        val cursor = database!!.rawQuery(sql, null)
        cursor.moveToFirst()
        val indexId = cursor.getColumnIndex("id")
        val indexSongName = cursor.getColumnIndex("songName")
        val indexArtistName = cursor.getColumnIndex("artistName")
        val indexLinkSong = cursor.getColumnIndex("linkSong")
        val indexLinkImage = cursor.getColumnIndex("linkImage")
        val indexLinkMusic = cursor.getColumnIndex("linkMusic")
        val indexTAlbumId = cursor.getColumnIndex("albumid")
        val indexCategoryId = cursor.getColumnIndex("categoryid")
        val id = cursor.getInt(indexId)

        val songName = cursor.getString(indexSongName)
        val artistName = cursor.getString(indexArtistName)
        val linkSong = cursor.getString(indexLinkSong)
        val linkImage = cursor.getString(indexLinkImage)
        val linkMusic = cursor.getString(indexLinkMusic)
        val albumid = cursor.getInt(indexTAlbumId)
        val categoryid = cursor.getInt(indexCategoryId)
        cursor.close()
        closeDatabase()
        return MusicData(id = id, songName = songName, artistName = artistName, linkSong = linkSong, linkImage = linkImage, linkMusic = linkMusic, albumid = albumid, categoryId = categoryid
        )
    }

    fun getMusicCategoryId(id: Int): MutableList<MusicData> {
        val musicData = mutableListOf<MusicData>()
        openDatabase()
        val sql = "SELECT * FROM music WHERE categoryid = $id"
        val cursor = database!!.rawQuery(sql, null)
        cursor.moveToFirst()
        val indexId = cursor.getColumnIndex("id")
        val indexSongName = cursor.getColumnIndex("songName")
        val indexArtistName = cursor.getColumnIndex("artistName")
        val indexLinkSong = cursor.getColumnIndex("linkSong")
        val indexLinkImage = cursor.getColumnIndex("linkImage")
        val indexLinkMusic = cursor.getColumnIndex("linkMusic")
        val indexTAlbumId = cursor.getColumnIndex("albumid")
        val indexCategoryId = cursor.getColumnIndex("categoryid")
        while (!cursor.isAfterLast) {
            val id = cursor.getInt(indexId)
            val songName = cursor.getString(indexSongName)
            val artistName = cursor.getString(indexArtistName)
            val linkSong = cursor.getString(indexLinkSong)
            val linkImage = cursor.getString(indexLinkImage)
            val linkMusic = cursor.getString(indexLinkMusic)
            val albumid = cursor.getInt(indexTAlbumId)
            val categoryid = cursor.getInt(indexCategoryId)
            musicData.add(
                MusicData(
                    id = id,
                    songName = songName,
                    artistName = artistName,
                    linkSong = linkSong,
                    linkImage = linkImage,
                    linkMusic = linkMusic,
                    albumid = albumid,
                    categoryId = categoryid
                )
            )
            cursor.moveToNext()
        }
        cursor.close()
        closeDatabase()

        return musicData
    }



    fun insertMusic(data: MusicData) {
        openDatabase()
        val contentValues = ContentValues()
        contentValues.put("songName", data.songName)
        contentValues.put("artistName", data.artistName)
        contentValues.put("linkSong", data.linkSong)
        contentValues.put("linkImage", data.linkImage)
        contentValues.put("linkMusic", data.linkMusic)
        contentValues.put("albumid", data.albumid)
        contentValues.put("categoryid", data.categoryId)

        database?.insert(name_table_1, null, contentValues)
        closeDatabase()
    }

    private fun openDatabase() {
        if (database == null || !database!!.isOpen) {
            val path = Environment.getDataDirectory().path +
                    File.separator + "data" +
                    File.separator + context.packageName +
                    File.separator + "db"
            val fullPath = path + File.separator + DB_Name
            database = SQLiteDatabase.openDatabase(
                fullPath, null,
                SQLiteDatabase.OPEN_READWRITE
            )
        }
    }

    private fun closeDatabase() {
        if (database != null && database!!.isOpen) {
            database?.close()
            database = null
        }
    }


}