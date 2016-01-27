
const React = require('react');
const ReactDOM = require('react-dom');

const data =
  [{ id: 1, description: 'facebook', uri: 'https://facebook.com' },
   { id: 2, description: 'twitter', uri: 'https://twitter.com' }];

const Bookmark = props => (
  <div className="bookmark">
    <h2 className="description">
      {props.description}
    </h2>
    {props.uri}
  </div>
);

Bookmark.propTypes = { description: React.PropTypes.string, uri: React.PropTypes.bool };

const BookmarksBox = props => (
  <div>
    <h1>Bookmarks</h1>
      <BookmarkList data={props.data}/>
      <BookmarkForm/>
  </div>
);

BookmarksBox.propTypes = { data: React.PropTypes.array };

const BookmarkList = props => (
  <div className="bookmarkList">
    {props.data.map(item =>
      <Bookmark description={item.description} uri={item.uri}/>)}
  </div>
);

BookmarkList.propTypes = { data: React.PropTypes.array };

const BookmarkForm = () => (
  <div className="bookmarkForm">
      Enter your bookmarks here
  </div>
);

ReactDOM.render(
  <BookmarksBox data={data}/>, document.getElementById('example')
);
