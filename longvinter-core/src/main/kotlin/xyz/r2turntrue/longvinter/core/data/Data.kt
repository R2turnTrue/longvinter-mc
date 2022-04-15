package xyz.r2turntrue.longvinter.core.data

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import xyz.r2turntrue.longvinter.core.data.table.PlayerTable
import java.io.File

lateinit var db: Database

fun connectDb() {
    val dbFile = File("longvinter.db")
    if(!dbFile.exists())
        dbFile.createNewFile()

    db = Database.connect("jdbc:sqlite:${dbFile.absolutePath}", driver = "org.sqlite.JDBC")

    transaction(db) {
        SchemaUtils.create(PlayerTable)
    }
}