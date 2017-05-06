package groovy.ru.egartech.service

import groovy.ru.egartech.domain.Product
import groovy.ru.egartech.domain.Storage
import groovy.transform.TypeChecked

@TypeChecked
class Distribution {

    // На вход "сервису" распределения подается набор существующих складов и товаров
    LinkedList<Product> products = [] // Набор товаров
    List<Storage> storages = []       // Набор складов

    void beginDistribution() {

        /*
        * Товары в списке products записаны в обратном порядке и берутся с конца списка.
        * Это сделано для того, чтобы при размещении товара на складе его можно было удалить из products
        * и при этом не нарушать порядок итерирования по коллекции.
        * В конечном итоге в products останутся только не размещенные ни на одном складе товары.
        */

        int i = products.size() - 1;
        for (int j = 0; j < storages.size(); ) {
            if (i < 0) break
            Product product = products[i]
            Storage storage = storages[j]
            if ((product.occupiedVolume <= storage.freeVolume) && (storage.catList.contains(product.category))) {
                storage.onAddingProduct(product)
                products.remove(i)
                i-- ; j = 0
            } else {
                if (storage != storages.last()) {
                    j++
                } else { i--; j = 0 }
            }
        }

        // Вывод результатов в консоль
        print "Заполнение складов:"
        storages.each {
            storage -> println "\n=> Склад ${storage.uniqName}, объем ${storage.capacity}, категории ${storage.catList}"
            for (product in storage.currentProducts) {
                print "${product.prodId} ${product.category} ${product.occupiedVolume} || "
                println "Идентификатор товара ${product.prodId}, категория ${product.category}," +
                        " занимаемый объем ${product.occupiedVolume}"
            }
        }
        println ""
        println "Нераспределенные товары:"
        if (products.size() == 0) println "Все товары распределены по складам"
        else products.reverse().each {
            print "${it.prodId} ${it.category} ${it.occupiedVolume} || "
            println "Идентификатор товара ${it.prodId}, категория ${it.category}, занимаемый объем ${it.occupiedVolume}"
        }

    }

}