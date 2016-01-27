
const React = require('react');
const ReactDOM = require('react-dom');
const $ = require('jQuery');

const Bookmark = props => (
  <div className="bookmark">
    <h2 className="description">
      {props.description}
    </h2>
    {props.uri}
  </div>
);

Bookmark.propTypes = { description: React.PropTypes.string, uri: React.PropTypes.string };

class BookmarksBox extends React.Component {
  constructor() {
    super();
    this.state = { data: [] };
  }
  componentDidMount() {
    const url = this.props.url;
    $.ajax({
      url,
      dataType: 'json',
      cache: false,
      success: data => {
        this.setState({ data });
      },
      error: (xhr, status, err) => {
        console.error(url, status, err.toString());
      }
    });
  }
  render() {
    return (
        <div>
          <h1>Bookmarks</h1>
            <BookmarkList data={this.state.data}/>
            <BookmarkForm/>
        </div>
    );
  }
}

BookmarksBox.propTypes = { url: React.PropTypes.string };

const BookmarkList = props => (
  <div className="bookmarkList">
    {props.data.map(item =>
      <Bookmark key={item.id} description={item.description} uri={item.uri}/>)}
  </div>
);

BookmarkList.propTypes = { data: React.PropTypes.array };

const BookmarkForm = () => (
  <div className="bookmarkForm">
    <br/>
      Enter your bookmarks here, man!
  </div>
);

ReactDOM.render(
  <BookmarksBox url="/janne/bookmarks"/>, document.getElementById('example')
);
