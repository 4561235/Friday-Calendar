//import logo from './logo.svg';
import './App.css';
import './components/Calendar/Calendar.jsx';
import Calendar from './components/Calendar/Calendar.jsx';
import DaySummary from './components/DaySummary/DaySummary.jsx';

function App() {
  return (
    <div className="App">
      {/* <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header> */}
      <DaySummary></DaySummary>
      <Calendar></Calendar>
    </div>
  );
}

export default App;
