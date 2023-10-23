import me.dio.copa.catar.data.source.MatchesDataSource
class EnableNotificationUseCase(
    private val localDataSource: MatchesDataSource.Local
) {
    suspend fun enableNotificationForMatch(matchId: String) {

        localDataSource.enableNotificationFor(matchId)
    }
}
