package com.morning.forum.demo.domain

/**
 * 図書館の蔵書
 * 蔵書とは図書館の管理化におかれた本
 */
data class Library(var id: Int = -1,
                   var isBorrowed: Boolean = false,
                   var book: Book,
                  )
