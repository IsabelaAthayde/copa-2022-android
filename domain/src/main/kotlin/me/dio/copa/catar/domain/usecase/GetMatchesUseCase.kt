import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import me.dio.copa.catar.data.source.MatchesDataSource
import me.dio.copa.catar.domain.model.Match

class GetMatchesUseCase(
    private val localDataSource: MatchesDataSource.Local,
    private val remoteDataSource: MatchesDataSource.Remote
) {
    fun getMatches(): Flow<List<Match>> {
        val localMatches = localDataSource.getMatches()
        val remoteMatches = remoteDataSource.getRemoteMatches()

        return localMatches.combine(remoteMatches) { local, remote ->
            if (local.isEmpty()) {
                remote
            } else {
                local
            }
        }
    }
}
