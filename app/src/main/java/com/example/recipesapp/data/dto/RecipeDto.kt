package com.example.recipesapp.data.dto

import com.google.gson.annotations.SerializedName

// что-то из этого может быть пустым
data class RecipeDto(
    @SerializedName("id") val id: Int,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("imageType") val imageType: String,
    @SerializedName("title") val title: String,
    @SerializedName("readyInMinutes") val readyInMinutes: Int,
    @SerializedName("servings") val servings: Int,
    @SerializedName("summary") val summary: String,
)

// val veryPopular: Boolean,
// val weightWatcherSmartPoints: Int,
/*"preparationMinutes": null,
"cookingMinutes": null,
"aggregateLikes": 12,
"healthScore": 23.0,
"cuisines": [
"Mediterranean",
"European",
"Greek"
],
"dishTypes": [
"lunch",
"main course",
"main dish",
"dinner"
],
"spoonacularScore": 79.13444519042969,
"spoonacularSourceUrl": "https://spoonacular.com/lamb-burgers-with-tzatziki-sauce-649195" */

/*data class VacancyDTO(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("alternate_url") val vacancyUrl: String,
    @SerializedName("salary") val salary: SalaryDTO? = null,
    @SerializedName("address") val address: AddressDTO? = null,
    @SerializedName("employer") val employer: EmployerDTO? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("key_skills") val keySkills: List<KeySkillDTO>? = null,
    @SerializedName("area") val area: AreaDTO,
    @SerializedName("experience") val experience: ExperienceDTO? = null,
    @SerializedName("schedule") val schedule: ScheduleDTO? = null,
    @SerializedName("employment") val employment: EmploymentDTO? = null,
    @SerializedName("published_at") val publishedAt: String
)*/