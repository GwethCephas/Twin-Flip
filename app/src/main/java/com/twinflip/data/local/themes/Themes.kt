package com.twinflip.data.local.themes

import com.twinflip.R
import com.twinflip.data.local.images.ImageItem
import com.twinflip.data.local.images.ThemeImages
import com.twinflip.data.utils.Constants.ANIMALS
import com.twinflip.data.utils.Constants.EMOJIS
import com.twinflip.data.utils.Constants.FANTASY
import com.twinflip.data.utils.Constants.FOODS_SNACKS
import com.twinflip.data.utils.Constants.FRUITS
import com.twinflip.data.utils.Constants.MUSIC
import com.twinflip.data.utils.Constants.NATURE
import com.twinflip.data.utils.Constants.SPACE
import com.twinflip.data.utils.Constants.SPORTS
import com.twinflip.data.utils.Constants.UNDERWATER
import com.twinflip.data.utils.Constants.VEHICLES

object Themes {

    val animals = ThemeImages(
        themeName = ANIMALS,
        images = listOf(
            ImageItem(
                id = R.drawable.animal_cat,
                name = "cat"
            ),
            ImageItem(
                id = R.drawable.animal_dog,
                name = "dog"
            ),
            ImageItem(
                id = R.drawable.animal_fox,
                name = "fox"
            ),
            ImageItem(
                id = R.drawable.animal_frog,
                name = "frog"
            ),
            ImageItem(
                id = R.drawable.animal_koala,
                name = "koala"
            ),
            ImageItem(
                id = R.drawable.animal_lion,
                name = "lion"
            ),
            ImageItem(
                id = R.drawable.animal_monkey,
                name = "monkey"
            ),
            ImageItem(
                id = R.drawable.animal_panda,
                name = "panda"
            ),
            ImageItem(
                id = R.drawable.animal_pig,
                name = "pig"
            ),
            ImageItem(
                id = R.drawable.animal_tiger,
                name = "tiger"
            ),
        )
    )

    val emojis = ThemeImages(
        themeName = EMOJIS,
        images = listOf(
            ImageItem(
                id = R.drawable.emoji_blue,
                name = "blue"
            ),
            ImageItem(
                id = R.drawable.emoji_cool,
                name = "cool"
            ),
            ImageItem(
                id = R.drawable.emoji_pink,
                name = "pink"
            ),
            ImageItem(
                id = R.drawable.emoji_angry,
                name = "angry"
            ),
            ImageItem(
                id = R.drawable.emoji_demon,
                name = "demon"
            ),
            ImageItem(
                id = R.drawable.emoji_crying,
                name = "crying"
            ),
            ImageItem(
                id = R.drawable.emoji_laughing,
                name = "laughing"
            ),
            ImageItem(
                id = R.drawable.emoji_sleeping,
                name = "sleeping"
            ),
            ImageItem(
                id = R.drawable.emoji_winking,
                name = "winking"
            ),
            ImageItem(
                id = R.drawable.emoji_happy,
                name = "happy"
            )
        )
    )

    val fantasies = ThemeImages(
        themeName = FANTASY,
        images = listOf(
            ImageItem(
                id = R.drawable.fantasy_dragon,
                name = "dragon"
            ),
            ImageItem(
                id = R.drawable.fantasy_shield,
                name = "shield"
            ),
            ImageItem(
                id = R.drawable.fantasy_alien,
                name = "alien"
            ),
            ImageItem(
                id = R.drawable.fantasy_crown,
                name = "crown"
            ),
            ImageItem(
                id = R.drawable.fantasy_crystal_ball,
                name = "crystal ball"
            ),
            ImageItem(
                id = R.drawable.fantasy_hat_and_magic,
                name = "hat and magic"
            ),
            ImageItem(
                id = R.drawable.fantasy_magic_wand,
                name = "magic wand"
            ),
            ImageItem(
                id = R.drawable.fantasy_potion_bottle,
                name = "potion bottle"
            ),
            ImageItem(
                id = R.drawable.fantasy_santa_claus,
                name = "santa claus"
            ),
            ImageItem(
                id = R.drawable.fantasy_sword,
                name = "sword"
            )
        )
    )
    val foodsAndSnacks = ThemeImages(
        themeName = FOODS_SNACKS,
        images = listOf(
            ImageItem(
                id = R.drawable.food_burger,
                name = "burger"
            ),
            ImageItem(
                id = R.drawable.food_chicken_drumsticks,
                name = "drumsticks"
            ),
            ImageItem(
                id = R.drawable.food_cupcake,
                name = "cupcake"
            ),
            ImageItem(
                id = R.drawable.food_donut,
                name = "donut"
            ),
            ImageItem(
                id = R.drawable.food_frenchfries,
                name = "frenchFries"
            ),
            ImageItem(
                id = R.drawable.food_hotdog,
                name = "hotDog"
            ),
            ImageItem(
                id = R.drawable.food_icecream,
                name = "iceCream"
            ),
            ImageItem(
                id = R.drawable.food_pizza,
                name = "pizza"
            ),
            ImageItem(
                id = R.drawable.food_taco,
                name = "taco"
            ),
            ImageItem(
                id = R.drawable.food_tropicaldrink,
                name = "tropicalDrink"
            )
        )
    )
    val fruits = ThemeImages(
        themeName = FRUITS,
        images = listOf(
            ImageItem(
                id = R.drawable.fruit_apple,
                name = "apple"
            ),
            ImageItem(
                id = R.drawable.fruit_banana,
                name = "banana"
            ),
            ImageItem(
                id = R.drawable.fruit_cherry,
                name = "cherry"
            ),
            ImageItem(
                id = R.drawable.fruit_grapes,
                name = "grapes"
            ),
            ImageItem(
                id = R.drawable.fruit_kiwi,
                name = "kiwi"
            ),
            ImageItem(
                id = R.drawable.fruit_mango,
                name = "mango"
            ),
            ImageItem(
                id = R.drawable.fruit_orange,
                name = "orange"
            ),
            ImageItem(
                id = R.drawable.fruit_pineapple,
                name = "pineapple"
            ),
            ImageItem(
                id = R.drawable.fruit_strawberry,
                name = "strawberry"
            ),
            ImageItem(
                id = R.drawable.fruit_watermelon,
                name = "watermelon"
            )
        )
    )
    val music = ThemeImages(
        themeName = MUSIC,
        images = listOf(
            ImageItem(
                id = R.drawable.music_drum,
                name = "drum"
            ),
            ImageItem(
                id = R.drawable.music_guitar,
                name = "guitar"
            ),
            ImageItem(
                id = R.drawable.music_headphones,
                name = "headPhones"
            ),
            ImageItem(
                id = R.drawable.music_microphone,
                name = "microphone"
            ),
            ImageItem(
                id = R.drawable.music_notes,
                name = "musicNotes"
            ),
            ImageItem(
                id = R.drawable.music_piano,
                name = "piano"
            ),
            ImageItem(
                id = R.drawable.music_player,
                name = "musicPlayer"
            ),
            ImageItem(
                id = R.drawable.music_saxophone,
                name = "saxophone"
            ),
            ImageItem(
                id = R.drawable.music_speaker,
                name = "speaker"
            ),
            ImageItem(
                id = R.drawable.music_violin,
                name = "violin"
            )
        )
    )
    val space = ThemeImages(
        themeName = SPACE,
        images = listOf(
            ImageItem(
                id = R.drawable.space_astronaut,
                name = "astronaut"
            ),
            ImageItem(
                id = R.drawable.space_comet,
                name = "comet"
            ),
            ImageItem(
                id = R.drawable.space_galaxy,
                name = "galaxy"
            ),
            ImageItem(
                id = R.drawable.space_moon,
                name = "moon"
            ),
            ImageItem(
                id = R.drawable.space_rocket,
                name = "rocket"
            ),
            ImageItem(
                id = R.drawable.space_saturn,
                name = "saturn"
            ),
            ImageItem(
                id = R.drawable.space_star,
                name = "star"
            ),
            ImageItem(
                id = R.drawable.space_sun_face,
                name = "sunFace"
            ),
            ImageItem(
                id = R.drawable.space_telescope,
                name = "telescope"
            ),
            ImageItem(
                id = R.drawable.space_ufo,
                name = "ufo"
            )
        )
    )
    val sports = ThemeImages(
        themeName = SPORTS,
        images = listOf(
            ImageItem(
                id = R.drawable.sport_basketball,
                name = "basketball"
            ),
            ImageItem(
                id = R.drawable.sport_boxing_glove,
                name = "guitar"
            ),
            ImageItem(
                id = R.drawable.sport_cricket,
                name = "cricket"
            ),
            ImageItem(
                id = R.drawable.sport_whistle,
                name = "whistle"
            ),
            ImageItem(
                id = R.drawable.sport_volleyball,
                name = "volleyball"
            ),
            ImageItem(
                id = R.drawable.sport_trophy,
                name = "trophy"
            ),
            ImageItem(
                id = R.drawable.sport_tennis_ball,
                name = "tennis"
            ),
            ImageItem(
                id = R.drawable.sport_rugby,
                name = "rugby"
            ),
            ImageItem(
                id = R.drawable.sport_football,
                name = "football"
            ),
            ImageItem(
                id = R.drawable.sport_dart,
                name = "violin"
            )
        )
    )
    val underwater = ThemeImages(
        themeName = UNDERWATER,
        images = listOf(
            ImageItem(
                id = R.drawable.water_crab,
                name = "crab"
            ),
            ImageItem(
                id = R.drawable.water_dolphin,
                name = "dolphin"
            ),
            ImageItem(
                id = R.drawable.water_fish,
                name = "headPhones"
            ),
            ImageItem(
                id = R.drawable.water_octopus,
                name = "octopus"
            ),
            ImageItem(
                id = R.drawable.water_shark,
                name = "shark"
            ),
            ImageItem(
                id = R.drawable.water_starfish,
                name = "starfish"
            ),
            ImageItem(
                id = R.drawable.water_submarine,
                name = "submarine"
            ),
            ImageItem(
                id = R.drawable.water_turtle,
                name = "turtle"
            ),
            ImageItem(
                id = R.drawable.water_walrus,
                name = "walrus"
            ),
            ImageItem(
                id = R.drawable.water_whale,
                name = "whale"
            )
        )
    )
    val vehicles = ThemeImages(
        themeName = VEHICLES,
        images = listOf(
            ImageItem(
                id = R.drawable.vehicle_airplane,
                name = "airplane"
            ),
            ImageItem(
                id = R.drawable.vehicle_ambulance,
                name = "ambulance"
            ),
            ImageItem(
                id = R.drawable.vehicle_boat,
                name = "boat"
            ),
            ImageItem(
                id = R.drawable.vehicle_bus,
                name = "bus"
            ),
            ImageItem(
                id = R.drawable.vehicle_car,
                name = "car"
            ),
            ImageItem(
                id = R.drawable.vehicle_fire_truck,
                name = "fireTruck"
            ),
            ImageItem(
                id = R.drawable.vehicle_helicopter,
                name = "helicopter"
            ),
            ImageItem(
                id = R.drawable.vehicle_motorcycle,
                name = "motorcycle"
            ),
            ImageItem(
                id = R.drawable.vehicle_police_car,
                name = "policeCar"
            ),
            ImageItem(
                id = R.drawable.vehicle_train,
                name = "train"
            )
        )
    )
    val nature = ThemeImages(
        themeName = NATURE,
        images = listOf(
            ImageItem(
                id = R.drawable.nature_butterfly,
                name = "butterfly"
            ),
            ImageItem(
                id = R.drawable.nature_cloud,
                name = "cloud"
            ),
            ImageItem(
                id = R.drawable.nature_flower,
                name = "flower"
            ),
            ImageItem(
                id = R.drawable.nature_leaf,
                name = "leaf"
            ),
            ImageItem(
                id = R.drawable.nature_mountain,
                name = "mountain"
            ),
            ImageItem(
                id = R.drawable.nature_mushroom,
                name = "mushroom"
            ),
            ImageItem(
                id = R.drawable.nature_river,
                name = "river"
            ),
            ImageItem(
                id = R.drawable.nature_sun,
                name = "sun"
            ),
            ImageItem(
                id = R.drawable.nature_tree,
                name = "tree"
            )
        )
    )

}