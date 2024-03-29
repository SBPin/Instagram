# Project 3 - Instagram

Instagram is a photo sharing app using Parse as its backend.

Time spent: 18 hours spent in total

## User Stories

The following **required** functionality is completed:

- [x] User sees app icon in home screen.
- [x] User can sign up to create a new account using Parse authentication
- [x] User can log in and log out of his or her account
- [x] The current signed in user is persisted across app restarts
- [x] User can take a photo, add a caption, and post it to "Instagram"
- [x] User can view the last 20 posts submitted to "Instagram"
- [x] User can pull to refresh the last 20 posts submitted to "Instagram"
- [x] User can tap a post to view post details, including timestamp and caption.

The following **stretch** features are implemented:

- [x] Style the login page to look like the real Instagram login page.
- [x] Style the feed to look like the real Instagram feed.
- [x] User should switch between different tabs - viewing all posts (feed view), capture (camera and photo gallery view) and profile tabs (posts made) using a Bottom Navigation View.
- [x] User can load more posts once he or she reaches the bottom of the feed using infinite scrolling.
- [x] Show the username and creation time for each post
- [ ] After the user submits a new post, show an indeterminate progress bar while the post is being uploaded to Parse
- User Profiles:
  - [x] Allow the logged in user to add a profile photo
  - [x] Display the profile photo with each post
  - [ ] Tapping on a post's username or profile photo goes to that user's profile page
- [ ] User can comment on a post and see all comments for each post in the post details screen.
- [x] User can like a post and see number of likes for each post in the post details screen.
- [ ] Create a custom Camera View on your phone.
- [x] Run your app on your phone and use the camera to take the photo

The following **additional** features are implemented:

- [ ] List anything else that you can get done to improve the app functionality!

Please list two areas of the assignment you'd like to **discuss further with your peers** during the next class (examples include better ways to implement something, how to extend your app in certain ways, etc):

1. In the end, I realized that I had some difficulty with RecyclerViews conceptually. I did not realize how I should be clearing/resetting some things until the very end, when my app was crashing. This was an important lesson to learn, but I wish I had learned it before the last hour.
2. I also had some more issues with my app crashing after my first submission. I tried debugging and looking through all of the affected classes, and it boiled down to my an issue with accessing a post's associated username when a user was null. However, after looking through my database I realized that the problem was not within my code, but rather that when I had deleted a user, their corresponding posts had not deleted. This meant that some posts that were being loaded did not have assocaited users, and were crashing my app. This made me realize that I needed to check all aspects of my app for bugs, and not just assume that all of my problems are contained within Android Studio.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

Logging in as an existing user:


<img src='https://github.com/SBPin/Instagram/blob/master/Instagram.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

Creating a new account:


<img src='https://github.com/SBPin/Instagram/blob/master/InstagramNewUser.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Credits

List an 3rd party libraries, icons, graphics, or other assets you used in your app.

- [Android Async Http Client](http://loopj.com/android-async-http/) - networking library


## Notes

I encountered a massive setback in getting my application to post. For the first three days of working on the app, my posts showed up in my Parse Dashboard, but did not display in my my feeds. However, after a few hours of debugging, I was able to determine that I had innappropriately added a log statement that called on a user that had not yet been initialized. Up until now, I did not think or consider that a log statement (something used for debugging) could be causing my app to crash.

Overall, I really enjoyed the project ebcause I was able to implement much of it on my own. I liked being able to generate a databse, and add more columns/attributes as I went along, to accommodate for the additional features within my app.
## License

    Copyright 2019 Sabrina Pin

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
