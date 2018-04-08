package v1

import org.scalatest.{FlatSpec, Matchers}

class StockFinderSpec extends FlatSpec with Matchers with ShoppingCartsFixture {
  val itemFinder = StockFinder(stock)

  "An item finder" should "find the item specified by its name" in {
    itemFinder("A") shouldBe apple
    itemFinder("O") shouldBe orange
  }

  it should "throw ItemNotFoundException if the item was not found " in {
    intercept[ItemNotFoundException](itemFinder("B")).getMessage shouldBe "Item [B] was not found"
  }

}
