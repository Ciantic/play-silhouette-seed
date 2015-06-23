package models.daos

import com.mohiva.play.silhouette.api.{ LoginInfo, AuthInfo }
import com.mohiva.play.silhouette.impl.daos.DelegableAuthInfoDAO
import com.mohiva.play.silhouette.api.repositories.AuthInfoRepository
import play.api.libs.concurrent.Execution.Implicits._

import scala.collection.mutable
import scala.concurrent.Future
import scala.reflect.ClassTag

/**
 * The DAO to store the OAuth1 information.
 *
 * Note: Not thread safe, demo only.
 */
class AuthInfoRepositoryImpl[C <: AuthInfo](implicit tag: ClassTag[C]) extends AuthInfoRepository {
  /**
   * Finds the auth info which is linked with the specified login info.
   *
   * @param loginInfo The linked login info.
   * @return The retrieved auth info or None if no auth info could be retrieved for the given login info.
   */
  def find[T <: AuthInfo](loginInfo: LoginInfo)(implicit tag: scala.reflect.ClassTag[T]): Future[Option[T]] = {
    Future.successful(None)
  }
  
  /**
   * Adds new auth info for the given login info.
   *
   * @param loginInfo The login info for which the auth info should be added.
   * @param authInfo The auth info to add.
   * @return The added auth info.
   */
  def add[T <: AuthInfo](loginInfo: LoginInfo, authInfo: T): Future[T] = {
    Future.successful(authInfo)
  }

  /**
   * Updates the auth info for the given login info.
   *
   * @param loginInfo The login info for which the auth info should be updated.
   * @param authInfo The auth info to update.
   * @return The updated auth info.
   */
  def update[T <: AuthInfo](loginInfo: LoginInfo, authInfo: T): Future[T] = {
    Future.successful(authInfo)
  }

  /**
   * Saves the auth info for the given login info.
   *
   * This method either adds the auth info if it doesn't exists or it updates the auth info
   * if it already exists.
   *
   * @param loginInfo The login info for which the auth info should be saved.
   * @param authInfo The auth info to save.
   * @return The saved auth info.
   */
  def save[T <: AuthInfo](loginInfo: LoginInfo, authInfo: T): Future[T] = {
    find(loginInfo).flatMap {
      case Some(_) => update(loginInfo, authInfo)
      case None => add(loginInfo, authInfo)
    }
  }

  /**
   * Removes the auth info for the given login info.
   *
   * @param loginInfo The login info for which the auth info should be removed.
   * @return A future to wait for the process to be completed.
   */
  def remove[T <: AuthInfo](loginInfo: LoginInfo)(implicit tag: scala.reflect.ClassTag[T]): Future[Unit] = {
    Future.successful(())
  }


}