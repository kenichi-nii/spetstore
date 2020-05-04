package modules

import com.google.inject.AbstractModule
import domains.item.ItemRepository
import infrastructures.item.ItemRepositoryJdbc

class PetStoreModule extends AbstractModule {

  @SuppressWarnings(Array("org.wartremover.warts.NonUnitStatements"))
  override def configure(): Unit = {
    bind(classOf[ItemRepository]).to(classOf[ItemRepositoryJdbc])
  }

}
