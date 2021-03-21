package com.morning.forum.demo.web.resolvers


import com.coxautodev.graphql.tools.GraphQLQueryResolver

import com.morning.forum.demo.domain.Library
import com.morning.forum.demo.infrastructure.repository.LibraryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class LibraryQueryResolver : GraphQLQueryResolver {
    @Autowired
    lateinit var libraryRepository : LibraryRepository

    fun search(title: String): List<Library> {
        return libraryRepository.findByTitle(title)
    }

}