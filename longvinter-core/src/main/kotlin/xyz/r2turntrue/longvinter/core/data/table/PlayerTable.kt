package xyz.r2turntrue.longvinter.core.data.table

import org.jetbrains.exposed.sql.Table

object PlayerTable: Table() {

    val id = varchar("id", 36) // uuid
    val mk = integer("mk").nullable().default(0)

    override val primaryKey = PrimaryKey(id)

}