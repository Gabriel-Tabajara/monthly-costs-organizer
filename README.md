# Monthly costs organizer

An API to organize my monthly costs

## Features

### 1 - Cost management
- Add a cost - Route✅ - Front-end
- Get all costs by month - Route✅ - Front-end
- Delete a cost - Route✅ - Front-end
- Filter by the most expensive to the least and vice versa - Front-end
- Filter the costs by week in month - Front-end
- Put in order by day - Order Route✅
### 2 - Categories
- Add a category (name, month and limit, no limit per month should be an option)
- Add a cost into a category
- Get a category with all costs on it
- Edit a category
- Delete a category
- Delete a cost from a category
- Add this category in next month categories without the costs
- Get the difference between the limit and the total spent
### 3 - Locals
- When a new cost is created a new local is created if it does not exists
- Filter costs by local and display the values per month
### 4 - Month limitations and add limit between categories
- Add a spend limit per month
- Transfer the limit between categories, not increasing the total amount

## Entitys

- MonthYear
    - Cost[]
        - id
        - value
        - day
        - local 
        - category 
- CATEGORY 
    - id 
    - name
    - limit
    - costId[]
- LOCAL 
    - id
    - name 
