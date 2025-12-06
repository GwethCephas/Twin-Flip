package com.twinflip.data.local.datasource

import com.twinflip.R
import com.twinflip.data.local.model.CardEntity
import com.twinflip.data.local.model.ThemeEntity
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
    val animals = ThemeEntity(
        themeName = ANIMALS,
        images = listOf(
            CardEntity(
                imageRes = R.drawable.animal_cat,
                name = "cat"
            ),
            CardEntity(
                imageRes = R.drawable.animal_dog,
                name = "dog"
            ),
            CardEntity(
                imageRes = R.drawable.animal_fox,
                name = "fox"
            ),
            CardEntity(
                imageRes = R.drawable.animal_frog,
                name = "frog"
            ),
            CardEntity(
                imageRes = R.drawable.animal_koala,
                name = "koala"
            ),
            CardEntity(
                imageRes = R.drawable.animal_lion,
                name = "lion"
            ),
            CardEntity(
                imageRes = R.drawable.animal_monkey,
                name = "monkey"
            ),
            CardEntity(
                imageRes = R.drawable.animal_panda,
                name = "panda"
            ),
            CardEntity(
                imageRes = R.drawable.animal_pig,
                name = "pig"
            ),
            CardEntity(
                imageRes = R.drawable.animal_tiger,
                name = "tiger"
            ),
        )
    )

    val emojis = ThemeEntity(
        themeName = EMOJIS,
        images = listOf(
            CardEntity(
                imageRes = R.drawable.emoji_blue,
                name = "blue"
            ),
            CardEntity(
                imageRes = R.drawable.emoji_cool,
                name = "cool"
            ),
            CardEntity(
                imageRes = R.drawable.emoji_pink,
                name = "pink"
            ),
            CardEntity(
                imageRes = R.drawable.emoji_angry,
                name = "angry"
            ),
            CardEntity(
                imageRes = R.drawable.emoji_demon,
                name = "demon"
            ),
            CardEntity(
                imageRes = R.drawable.emoji_crying,
                name = "crying"
            ),
            CardEntity(
                imageRes = R.drawable.emoji_laughing,
                name = "laughing"
            ),
            CardEntity(
                imageRes = R.drawable.emoji_sleeping,
                name = "sleeping"
            ),
            CardEntity(
                imageRes = R.drawable.emoji_winking,
                name = "winking"
            ),
            CardEntity(
                imageRes = R.drawable.emoji_happy,
                name = "happy"
            )
        )
    )

    val fantasies = ThemeEntity(
        themeName = FANTASY,
        images = listOf(
            CardEntity(
                imageRes = R.drawable.fantasy_dragon,
                name = "dragon"
            ),
            CardEntity(
                imageRes = R.drawable.fantasy_shield,
                name = "shield"
            ),
            CardEntity(
                imageRes = R.drawable.fantasy_alien,
                name = "alien"
            ),
            CardEntity(
                imageRes = R.drawable.fantasy_crown,
                name = "crown"
            ),
            CardEntity(
                imageRes = R.drawable.fantasy_crystal_ball,
                name = "crystal ball"
            ),
            CardEntity(
                imageRes = R.drawable.fantasy_hat_and_magic,
                name = "hat and magic"
            ),
            CardEntity(
                imageRes = R.drawable.fantasy_magic_wand,
                name = "magic wand"
            ),
            CardEntity(
                imageRes = R.drawable.fantasy_potion_bottle,
                name = "potion bottle"
            ),
            CardEntity(
                imageRes = R.drawable.fantasy_santa_claus,
                name = "santa claus"
            ),
            CardEntity(
                imageRes = R.drawable.fantasy_sword,
                name = "sword"
            )
        )
    )
    val foodsAndSnacks = ThemeEntity(
        themeName = FOODS_SNACKS,
        images = listOf(
            CardEntity(
                imageRes = R.drawable.food_burger,
                name = "burger"
            ),
            CardEntity(
                imageRes = R.drawable.food_chicken_drumsticks,
                name = "drumsticks"
            ),
            CardEntity(
                imageRes = R.drawable.food_cupcake,
                name = "cupcake"
            ),
            CardEntity(
                imageRes = R.drawable.food_donut,
                name = "donut"
            ),
            CardEntity(
                imageRes = R.drawable.food_frenchfries,
                name = "frenchFries"
            ),
            CardEntity(
                imageRes = R.drawable.food_hotdog,
                name = "hotDog"
            ),
            CardEntity(
                imageRes = R.drawable.food_icecream,
                name = "iceCream"
            ),
            CardEntity(
                imageRes = R.drawable.food_pizza,
                name = "pizza"
            ),
            CardEntity(
                imageRes = R.drawable.food_taco,
                name = "taco"
            ),
            CardEntity(
                imageRes = R.drawable.food_tropicaldrink,
                name = "tropicalDrink"
            )
        )
    )
    val fruits = ThemeEntity(
        themeName = FRUITS,
        images = listOf(
            CardEntity(
                imageRes = R.drawable.fruit_apple,
                name = "apple"
            ),
            CardEntity(
                imageRes = R.drawable.fruit_banana,
                name = "banana"
            ),
            CardEntity(
                imageRes = R.drawable.fruit_cherry,
                name = "cherry"
            ),
            CardEntity(
                imageRes = R.drawable.fruit_grapes,
                name = "grapes"
            ),
            CardEntity(
                imageRes = R.drawable.fruit_kiwi,
                name = "kiwi"
            ),
            CardEntity(
                imageRes = R.drawable.fruit_mango,
                name = "mango"
            ),
            CardEntity(
                imageRes = R.drawable.fruit_orange,
                name = "orange"
            ),
            CardEntity(
                imageRes = R.drawable.fruit_pineapple,
                name = "pineapple"
            ),
            CardEntity(
                imageRes = R.drawable.fruit_strawberry,
                name = "strawberry"
            ),
            CardEntity(
                imageRes = R.drawable.fruit_watermelon,
                name = "watermelon"
            )
        )
    )
    val music = ThemeEntity(
        themeName = MUSIC,
        images = listOf(
            CardEntity(
                imageRes = R.drawable.music_drum,
                name = "drum"
            ),
            CardEntity(
                imageRes = R.drawable.music_guitar,
                name = "guitar"
            ),
            CardEntity(
                imageRes = R.drawable.music_headphones,
                name = "headPhones"
            ),
            CardEntity(
                imageRes = R.drawable.music_microphone,
                name = "microphone"
            ),
            CardEntity(
                imageRes = R.drawable.music_notes,
                name = "musicNotes"
            ),
            CardEntity(
                imageRes = R.drawable.music_piano,
                name = "piano"
            ),
            CardEntity(
                imageRes = R.drawable.music_player,
                name = "musicPlayer"
            ),
            CardEntity(
                imageRes = R.drawable.music_saxophone,
                name = "saxophone"
            ),
            CardEntity(
                imageRes = R.drawable.music_speaker,
                name = "speaker"
            ),
            CardEntity(
                imageRes = R.drawable.music_violin,
                name = "violin"
            )
        )
    )
    val space = ThemeEntity(
        themeName = SPACE,
        images = listOf(
            CardEntity(
                imageRes = R.drawable.space_astronaut,
                name = "astronaut"
            ),
            CardEntity(
                imageRes = R.drawable.space_comet,
                name = "comet"
            ),
            CardEntity(
                imageRes = R.drawable.space_galaxy,
                name = "galaxy"
            ),
            CardEntity(
                imageRes = R.drawable.space_moon,
                name = "moon"
            ),
            CardEntity(
                imageRes = R.drawable.space_rocket,
                name = "rocket"
            ),
            CardEntity(
                imageRes = R.drawable.space_saturn,
                name = "saturn"
            ),
            CardEntity(
                imageRes = R.drawable.space_star,
                name = "star"
            ),
            CardEntity(
                imageRes = R.drawable.space_sun_face,
                name = "sunFace"
            ),
            CardEntity(
                imageRes = R.drawable.space_telescope,
                name = "telescope"
            ),
            CardEntity(
                imageRes = R.drawable.space_ufo,
                name = "ufo"
            )
        )
    )
    val sports = ThemeEntity(
        themeName = SPORTS,
        images = listOf(
            CardEntity(
                imageRes = R.drawable.sport_basketball,
                name = "basketball"
            ),
            CardEntity(
                imageRes = R.drawable.sport_boxing_glove,
                name = "guitar"
            ),
            CardEntity(
                imageRes = R.drawable.sport_cricket,
                name = "cricket"
            ),
            CardEntity(
                imageRes = R.drawable.sport_whistle,
                name = "whistle"
            ),
            CardEntity(
                imageRes = R.drawable.sport_volleyball,
                name = "volleyball"
            ),
            CardEntity(
                imageRes = R.drawable.sport_trophy,
                name = "trophy"
            ),
            CardEntity(
                imageRes = R.drawable.sport_tennis_ball,
                name = "tennis"
            ),
            CardEntity(
                imageRes = R.drawable.sport_rugby,
                name = "rugby"
            ),
            CardEntity(
                imageRes = R.drawable.sport_football,
                name = "football"
            ),
            CardEntity(
                imageRes = R.drawable.sport_dart,
                name = "violin"
            )
        )
    )
    val underwater = ThemeEntity(
        themeName = UNDERWATER,
        images = listOf(
            CardEntity(
                imageRes = R.drawable.water_crab,
                name = "crab"
            ),
            CardEntity(
                imageRes = R.drawable.water_dolphin,
                name = "dolphin"
            ),
            CardEntity(
                imageRes = R.drawable.water_fish,
                name = "headPhones"
            ),
            CardEntity(
                imageRes = R.drawable.water_octopus,
                name = "octopus"
            ),
            CardEntity(
                imageRes = R.drawable.water_shark,
                name = "shark"
            ),
            CardEntity(
                imageRes = R.drawable.water_starfish,
                name = "starfish"
            ),
            CardEntity(
                imageRes = R.drawable.water_submarine,
                name = "submarine"
            ),
            CardEntity(
                imageRes = R.drawable.water_turtle,
                name = "turtle"
            ),
            CardEntity(
                imageRes = R.drawable.water_walrus,
                name = "walrus"
            ),
            CardEntity(
                imageRes = R.drawable.water_whale,
                name = "whale"
            )
        )
    )
    val vehicles = ThemeEntity(
        themeName = VEHICLES,
        images = listOf(
            CardEntity(
                imageRes = R.drawable.vehicle_airplane,
                name = "airplane"
            ),
            CardEntity(
                imageRes = R.drawable.vehicle_ambulance,
                name = "ambulance"
            ),
            CardEntity(
                imageRes = R.drawable.vehicle_boat,
                name = "boat"
            ),
            CardEntity(
                imageRes = R.drawable.vehicle_bus,
                name = "bus"
            ),
            CardEntity(
                imageRes = R.drawable.vehicle_car,
                name = "car"
            ),
            CardEntity(
                imageRes = R.drawable.vehicle_fire_truck,
                name = "fireTruck"
            ),
            CardEntity(
                imageRes = R.drawable.vehicle_helicopter,
                name = "helicopter"
            ),
            CardEntity(
                imageRes = R.drawable.vehicle_motorcycle,
                name = "motorcycle"
            ),
            CardEntity(
                imageRes = R.drawable.vehicle_police_car,
                name = "policeCar"
            ),
            CardEntity(
                imageRes = R.drawable.vehicle_train,
                name = "train"
            )
        )
    )
    val nature = ThemeEntity(
        themeName = NATURE,
        images = listOf(
            CardEntity(
                imageRes = R.drawable.nature_butterfly,
                name = "butterfly"
            ),
            CardEntity(
                imageRes = R.drawable.nature_cloud,
                name = "cloud"
            ),
            CardEntity(
                imageRes = R.drawable.nature_flower,
                name = "flower"
            ),
            CardEntity(
                imageRes = R.drawable.nature_leaf,
                name = "leaf"
            ),
            CardEntity(
                imageRes = R.drawable.nature_mountain,
                name = "mountain"
            ),
            CardEntity(
                imageRes = R.drawable.nature_mushroom,
                name = "mushroom"
            ),
            CardEntity(
                imageRes = R.drawable.nature_river,
                name = "river"
            ),
            CardEntity(
                imageRes = R.drawable.nature_sun,
                name = "sun"
            ),
            CardEntity(
                imageRes = R.drawable.nature_tree,
                name = "tree"
            ),
            CardEntity(
                imageRes = R.drawable.nature_woods_forest,
                name = "woods"
            )
        )
    )

}