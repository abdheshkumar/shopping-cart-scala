package v1

import org.scalatest.{FlatSpec, Matchers}

class PriceSpec extends FlatSpec with Matchers {

  "Price" should "have an easy price creation" in {
    Price(1) shouldBe Price(1)
    Price(0.10) shouldBe Price(0.10)
  }

  it should "have a good price format" in {
    Price(0).format shouldBe "£0.00"
    Price(0.01).format shouldBe "£0.01"
    Price(1.03).format shouldBe "£1.03"
    Price(90.00).format shouldBe "£90.00"
  }

  it should "have a negated method that returns a Price with 'minus the amount'" in {
    Price(0).negated shouldBe Price(0)
    Price(-1).negated shouldBe Price(1)
    Price(-103).negated shouldBe Price(103)
    Price(-1000).negated shouldBe Price(1000)
  }

  it should "allow + between two Prices" in {
    Price(0) + Price(1) shouldBe Price(1)
    Price(-2) + Price(1) shouldBe Price(-1)
  }

  it should "allow - between two Prices" in {
    Price(0) - Price(1) shouldBe Price( - 1)
    Price(-4) - Price(1) shouldBe Price(-5)
  }

  it should "allow * n" in {
    Price(10) * 0 shouldBe Price(0)
    Price(10) * 3 shouldBe Price(30)
  }
}