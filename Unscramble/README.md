Unscramble app
=================================

Single player game app that displays scrambled words. To play the game, player has to make a
word using all the letters in the displayed scrambled word.
This code demonstrates the Android Architecture component- ViewModel and StateFlow.


What you'll learn
--------------
* Introduction to the Android app architecture
* How to use the ViewModel class in your app
* How to retain UI data through device configuration changes using a ViewModel

What you'll build
--------------
* An Unscramble game app where the user can guess the scrambled words

My Notes
--------------

### TEK YÖNLÜ VERİ AKIŞI 

Tek yönlü veri akışı (UDF), durumun aşağı ve olayların yukarı aktığı bir tasarım modelidir. Tek yönlü veri akışını izleyerek, kullanıcı arayüzünde durumu görüntüleyen bileşkenleri uygulamanızın durumu depolayan ve değiştiren bölümlerinden ayırabilirsiniz.

Tek yönlü veri akışı kullanan bir uygulama için UI güncelleme döngüsü aşağıdaki gibidir:

Event: Kullanıcı arabiriminin bir kısmı bir olay oluşturur ve onu yukarı doğru iletir (örneğin, işlenecek viewmodel'e iletilen bir düğme tıklaması gibi) veya uygulamanızın diğer katmanlarından iletilen bir olay (örneğin, kullanıcı oturumunun süresinin dolduğunun bir göstergesi gibi).

Update state: Bir olay işleyicisi durumu değiştirebilir.

Display state: State holder state'i iletir ve kullanıcı arayüzü onu görüntüler.

<img src="https://github.com/nasuh-unal/ComposeTutorial/assets/88931522/2bea8156-2cb6-450a-b8c5-4167ed1b0c72" width=480 height=337>

Uygulama mimarisi için UDF modelinin kullanılması aşağıdaki sonuçları doğurur:

* ViewModel, kullanıcı arayüzünün kullandığı durumu tutar ve sunar.
* UI durumu, ViewModel tarafından dönüştürülen uygulama verileridir.
* UI, kullanıcı olaylarını ViewModel'e bildirir.
* ViewModel kullanıcı eylemlerini işler ve durumu günceller.
* Güncellenen durum, işlenmek üzere kullanıcı arayüzüne geri beslenir.
* Bu süreç, durum değişikliğine neden olan her olay için tekrarlanır.

Uygulama mimarisi için UDF modelinin kullanımı aşağıdaki etkilere sahiptir:

* ViewModel, kullanıcı arayüzünün tükettiği durumu tutar ve gösterir.
* UI durumu, ViewModel tarafından dönüştürülen uygulama verileridir.
* Kullanıcı arabirimi, Viewmodel'e kullanıcı olaylarını bildirir.
* ViewModel, kullanıcı eylemlerini işler ve durumu günceller.
* Güncellenmiş durum, işlenecek kullanıcı arayüzüne geri beslenir.
* Bu işlem, bir durum mutasyonuna neden olan herhangi bir olay için tekrarlanır.

