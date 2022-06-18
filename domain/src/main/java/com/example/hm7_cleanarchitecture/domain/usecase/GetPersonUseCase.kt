package com.example.hm7_cleanarchitecture.domain.usecase

import com.example.hm7_cleanarchitecture.domain.model.LceState
import com.example.hm7_cleanarchitecture.domain.model.Person
import com.example.hm7_cleanarchitecture.domain.repository.PersonLocalRepository
import com.example.hm7_cleanarchitecture.domain.repository.PersonRemoteRepository
import kotlinx.coroutines.flow.*

class GetPersonUseCase(
    private val remoteRepository: PersonRemoteRepository,
    private val localRepository: PersonLocalRepository,
) {

    operator fun invoke(page: Int): Flow<LceState<List<Person>>> =
        flow {
            if(page == 1){
                val cache = localRepository.getPersonsFromDB(20, page)
                emit(LceState.Loading(cache))
            }

            remoteRepository.getPerson(page)
                .fold(
                    onSuccess = { list ->
                        localRepository.insertPersons(list, page)
                        if (list.size < 20) {
                            emit(LceState.Content(list, hasMoreData = false))
                        } else {
                            emit(LceState.Content(list))
                        }
                    },
                    onFailure = {
                        emit(LceState.Error(it))
                    }
                )
        }
}








