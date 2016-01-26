// main.js
var React = require('react');
var ReactDOM = require('react-dom');

const Bookmark = props => (
  <div className="bookmark">
    <h2 className="description">
      {props.description}
    </h2>
    {props.children}
  </div>
)

const BookmarksBox = () => (
  <div>
    <h1>Bookmarks</h1>
      <BookmarkList/>
      <BookmarkForm/>
  </div>
)

const BookmarkList = () => (
  <div className="bookmarkList">
    Hello, this the list of your bookmarks
    <Bookmark description="facebook">
      https://facebook.com
    </Bookmark>
    <Bookmark description="twitter">
      https://twitter.com
    </Bookmark>
  </div>
)

const BookmarkForm = () =>  (
  <div className="bookmarkForm">
      Enter your bookmarks here
  </div>
)

ReactDOM.render(
  <BookmarksBox/>,
  document.getElementById('example')
);
