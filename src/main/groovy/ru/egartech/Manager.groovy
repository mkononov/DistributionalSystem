package groovy.ru.egartech

import groovy.ru.egartech.domain.Product
import groovy.ru.egartech.domain.ProductCategoryEnum
import groovy.ru.egartech.domain.Storage
import groovy.ru.egartech.service.Distribution
import groovy.transform.TypeChecked

@TypeChecked // Проверка типов на этапе компиляции
class Manager {

    static void main(String[] args) {

        def distribution = new Distribution() // "Сервис" распределения

        // Формируем набор товаров
        def product1 = new Product(prodId: 1, category: ProductCategoryEnum.FOOD, occupiedVolume: 2)
        def product2 = new Product(prodId: 2, category: ProductCategoryEnum.BOOKS, occupiedVolume: 3)
        def product3 = new Product(prodId: 3, category: ProductCategoryEnum.ELECTRONICS, occupiedVolume: 1)
        def product4 = new Product(prodId: 4, category: ProductCategoryEnum.FOOD, occupiedVolume: 3)
        def product5 = new Product(prodId: 5, category: ProductCategoryEnum.ELECTRONICS, occupiedVolume: 2)
        def product6 = new Product(prodId: 6, category: ProductCategoryEnum.BOOKS, occupiedVolume: 2)
        def product7 = new Product(prodId: 7, category: ProductCategoryEnum.FOOD, occupiedVolume: 2)

        distribution.products.addFirst(product1)
        distribution.products.addFirst(product2)
        distribution.products.addFirst(product3)
        distribution.products.addFirst(product4)
        distribution.products.addFirst(product5)
        distribution.products.addFirst(product6)
        distribution.products.addFirst(product7)

        // Формируем набор складов
        def storage1 = new Storage("s1", 4, [ProductCategoryEnum.FOOD])
        def storage2 = new Storage("s2", 3, [ProductCategoryEnum.FOOD,
            ProductCategoryEnum.ELECTRONICS, ProductCategoryEnum.BOOKS])
        def storage3 = new Storage("s3", 6, [ProductCategoryEnum.ELECTRONICS])

        distribution.storages << storage1
        distribution.storages << storage2
        distribution.storages << storage3

        distribution.beginDistribution() // Начать распределение

    }

}