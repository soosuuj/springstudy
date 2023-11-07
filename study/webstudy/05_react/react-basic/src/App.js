import './App.css';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import Home from './component/Home';
import Ex01 from './component/Ex01';
import Header from './component/Header';
import Footer from './component/Footer';


function App() {
  return (
    <div className="App">
      {/* MyFristComponent 포함하기 */}
      {/* <MyFirstComponent /> */}

      {/* <Ex01 /> */}

      {/* 경로에 따른 컴포넌트 실행(react-router-dom: npm install react-rouer-dom) */}
      <BrowserRouter>
        <Header />
        <Routes>
            <Route path="/" element={<Home />} /> 
            <Route path="/ex01" element={<Ex01 />} />
          </Routes>
      </BrowserRouter>
      
      <Footer />
      
    </div>
  );
}

export default App;
