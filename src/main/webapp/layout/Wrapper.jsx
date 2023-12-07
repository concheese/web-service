import styled from "styled-components";

const Container = styled.div`
  max-width: 980px;
  margin: 0 auto;
  padding: 0 20px;
`;

export default function Wrapper({ children }) {
  return <Container>{children}</Container>;
}
