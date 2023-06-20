# USERSTORIES
## MVP
- Repo aufsetzen
  - je ein Repo für frontend und backend
### backend-spring
- Pakete
  - user
    - User
      - Long id
      - String name
      - String password
      - String role
      - int age
    - UserRepository (extends JpaRepository from jakarta persistence api)
    - UserController
    - UserDTO
    - UserService
  - Post
    - Long id
    - User user
    - Comment description
    - HashMap<Long, boolean>
  - Comment
    - Long id
    - User user
    - String text
  - Session
    - Profile Controller
    - 

### frontend-angular
- components
  - app / home
  - user (interface, .ts, .html)
  - post
    - html *ngFor="let post of posts"
    - <button [class.selected]="post === selectedPost" type="button" (click)="onSelect(post)">
    - css: modal pop-up // foregrounding of selected post and backgrounding other posts
    - <app-hero-detail [post]="selectedPost"></app-hero-detail>
    - {{ post.topComment | slice:0:50 }} // angular String method to display first 50 chars
  - comment

## ADD-ON 1
- Spring Security
- Profilbild
- SQL

## ADD-ON 2
- deployment